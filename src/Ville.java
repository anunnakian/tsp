import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by Mohamed on 2/17/2017.
 */
public class Ville {

    String nom;
    LinkedList<Route> voisins;
    int id;
    boolean visite;

    // Question 1.1
    public Ville(String nom, int id) {
        this.nom = nom;
        this.id = id;
        this.voisins = new LinkedList<>();
    }

    // Question 1.2
    public Route getRoute(Ville b) {
        for(Iterator<Route> it = voisins.iterator(); it.hasNext(); ){
            Route result = it.next();
            if (result.a.equals(this) && result.b.equals(b))
                return result;
        }
        return null;
    }

    // Question 1.3
    public static void visite(Ville v) {
        LinkedList<Route> l = v.voisins;
        v.visite = true;
        while (!l.isEmpty()) {
            Route route = l.removeFirst();
            if (!route.a.visite) visite(route.a);
            if (!route.b.visite) visite(route.b);
        }
    }
}
