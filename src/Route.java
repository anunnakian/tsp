/**
 * Created by Mohamed on 2/17/2017.
 */
public class Route {

    Ville a;
    Ville b;
    int distance;

    // Question 1.1
    public Route(Ville a, Ville b, int distance) {
        this.a = a;
        this.b = b;
        this.distance = distance;

        // si la route n'existe pas, on l'a crée
        if (!this.a.voisins.contains(this))
            this.a.voisins.add(this);

        if (!this.b.voisins.contains(this))
            this.b.voisins.add(this);
    }

}
