import java.util.LinkedList;

/**
 * Created by Mohamed on 2/17/2017.
 */
public class Tsp {

    // nb total des villes
    static int n;
    static Ville[] villes;
    static int distance[][];


    // Question 1.3
    public static boolean connexe() {
        for (int i = 0; i < n; i++)
            villes[i].visite = false;
        Ville.visite(villes[0]);
        for (int i = 0; i < n; i++)
            if (!villes[i].visite) return false;
        return true;
    }

//(distance[i][j] = distance[j][i], pour toutes villes i, j) et satisfaire l’inégalité triangulaire
//            (distance[i][j]  distance[i][k] + distance[k][j], pour toutes villes i, j, k).
//    Solution.
//    Comme le graphe est non-orienté et les valeurs des arêtes symétriques, un plus cours chemin de
//    i à j est aussi un plus court de j à i lorsqu’il est parcouru à l’envers. En conséquence distance
//    est symétrique.
//    Pour montrer que distance satisfait l’inégalité triangulaire, il suffit de noter que, comme le
//    graphe est connexe, il existe un chemin entre i et j (passant par k) de longueur distance[i][k]+
//    distance[k][j]. Puisque distance[i][j] est la longueur du plus petit chemin entre i et j, cette
//    quantité doit nécessairement être au plus la somme précédente.
//            b) Indiquez des algorithmes utilisables pour calculer cette matrice des distances ; précisez
//    éventuellement leurs complexités.


//            Solution.

//    c) En faisant un choix parmi ces algorithmes, écrivez une méthode
//    public static void calculDistances() de la classe Tsp qui initialise la matrice
//    distance[][] puis la remplit. Vous n’êtes pas obligés de choisir la méthode la plus efficace.
//    Solution.
//    Voici une solution utilisant Floyd-Warshall.
//


    public static void calculDistances() {
        distance = new int[n][n];
        boolean[][] unChemin = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            LinkedList<Route> voisins = villes[i].voisins;
            while (!voisins.isEmpty()) {
                Route route = voisins.removeFirst();
                distance[route.a.id][route.b.id] = route.distance;
                distance[route.b.id][route.a.id] = route.distance;
                unChemin[route.a.id][route.b.id] = true;
                unChemin[route.b.id][route.a.id] = true;
            }
        }
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (unChemin[i][k] && unChemin[k][j]) {
                        int distanceK = distance[i][k] + distance[k][j];
                        if ((distanceK < distance[i][j]) || !unChemin[i][j]))   {
                            distance[i][j] = distanceK;
                            distance[j][i] = distanceK;
                            unChemin[i][j] = true;
                            unChemin[j][i] = true;
                        }
                    }
                }
            }
        }
    }

    //    On peut utiliser l’algorithme de Floyd-Warshall (programmation dynamique), des produits itérés
//    sur la matrice des distances, ou bien faire des Dijkstra depuis chaque sommet.
//    Les deux premiers algorithmes utilisent naturellement un tableaux comme distance[][].
//    La complexité de Floyd-Warshall est o(n3). Le calcul de la puissance n-ième de la matrice des
//    longueurs d’arêtes peut être faite en log(n) produits. Avec une implémentation naïve du produit
//    de matrices, ça donne o(n3:log(n)) ; avec une implémentation sophistiquée on peut arriver à
//    o(n2;376:log(n)) (ce dernier point est limite hors-programme et n’est pas nécessaire pour avoir la
//    note maximale).
//    La complexité de Dijkstra dépend du nombre d’arêtes dans le graphe initial. S’il y a N arêtes,
//    l’algorithme de Dijkstra depuis un sommet est en O((n+N)log(N)) avec une file de priorité par
//    un tas binaire. On doit itérer cet algorithme n fois.
//    Si le graphe est relativement dense, n << N, il est préférable d’utiliser Floyd-Warshall. Si
//    le graphe est très creux, N = O(n), il est préférable d’utiliser l’itération de Dijkstra pour un
//    coût total en O(n2log(n)). (Là encore, les détails de cette analyse ne sont pas nécessairement
//    attendus.)
}
