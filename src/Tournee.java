/**
 * Created by Mohamed on 2/17/2017.
 */
public class Tournee {

    int[] t;

    Tournee() {
        t = new int[Tsp.n];
        for (int i = 0; i < Tsp.n; i++)
            t[i] = i;
    }

    public int longueur() {
        int longueur = Tsp.distance[t[Tsp.n - 1]][t[0]];
        for (int i = 0; i < Tsp.n - 1; i++)
            longueur = longueur + Tsp.distance[t[i]][t[i + 1]];
        return longueur;
    }

    public void plusProcheVoisin() {
        int n = t.length;
        for (int i = 1; i < n - 1; i++) {
            int suivant = i;
            for (int j = i + 1; j < n; j++) {
                if (Tsp.distance[t[i - 1]][t[j]] < Tsp.distance[t[i - 1]][t[suivant]]) {
                    suivant = j;
                }
            }
            int tmp = t[i];
            t[i] = t[suivant];
            t[suivant] = tmp;
        }
    }


    public Tournee amelioration() {
        int n = t.length;
        int gainMax = 0;
        int iMax = 0;
        int jMax = 0;
        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 2; j < n; j++) {
                int jNext = j + 1;
                if (jNext == n) jNext = 0;
                int gain = Tsp.distance[t[i]][t[i + 1]] + Tsp.distance[t[j]][t[jNext]]
                        - Tsp.distance[t[i]][t[j]] - Tsp.distance[t[i + 1]][t[jNext]];
                if (gain > gainMax) {
                    gainMax = gain;
                    iMax = i;
                    jMax = j;
                }
            }
        }
        if (gainMax == 0) {
            return null;
        } else {
            Tournee nouvelle = new Tournee();
            int index = 0;
            for (index = 0; index <= iMax; index++)
                nouvelle.t[index] = t[index];

            index = iMax + 1;

            for (int j = jMax; j >= iMax + 1; j--) {
                nouvelle.t[index] = t[j];
                index++;
            }

            for (int j = jMax + 1; j < n; j++)
                nouvelle.t[j] = t[j];
            return nouvelle;
        }
    }
}
