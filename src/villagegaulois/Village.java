package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
    private String nom;
    private Chef chef;
    private Gaulois[] villageois;
    private Etal[] etals;
    private int nbVillageois = 0;
    private int nbEtals;
    private Marche marche;

    public Village(String nom, int nbVillageoisMaximum, int nbEtals) {
        this.nom = nom;
        this.nbEtals = nbEtals;
        villageois = new Gaulois[nbVillageoisMaximum];
        etals = new Etal[nbEtals];
        for (int i = 0; i < nbEtals; i++) {
            etals[i] = new Etal();
        }
        marche = new Marche(nbEtals);
    }

    public String getNom() {
        return nom;
    }

    public void setChef(Chef chef) {
        this.chef = chef;
    }

    public void ajouterHabitant(Gaulois gaulois) {
        if (nbVillageois < villageois.length) {
            villageois[nbVillageois] = gaulois;
            nbVillageois++;
        }
    }

    public Gaulois trouverHabitant(String nomGaulois) {
        if (nomGaulois.equals(chef.getNom())) {
            return chef;
        }
        for (int i = 0; i < nbVillageois; i++) {
            Gaulois gaulois = villageois[i];
            if (gaulois.getNom().equals(nomGaulois)) {
                return gaulois;
            }
        }
        return null;
    }

    public String afficherVillageois() throws VillageSansChefException {
        if (chef == null) {
            throw new VillageSansChefException("Le village n'a pas de chef !");
        }

        StringBuilder chaine = new StringBuilder();
        if (nbVillageois < 1) {
            chaine.append("Il n'y a encore aucun habitant au village du chef "
                    + chef.getNom() + ".\n");
        } else {
            chaine.append("Au village du chef " + chef.getNom()
                    + " vivent les légendaires gaulois :\n");
            for (int i = 0; i < nbVillageois; i++) {
                chaine.append("- " + villageois[i].getNom() + "\n");
            }
        }
        return chaine.toString();
    }

    private static class Marche {
        private Etal[] etals;
        private int nbEtalVide;

        public Marche(int nbEtals) {
            etals = new Etal[nbEtals];
            for (int i = 0; i < nbEtals; i++) {
                etals[i] = new Etal();
            }
            nbEtalVide = nbEtals;
        }
    }

    public class VillageSansChefException extends Exception {
        public VillageSansChefException(String message) {
            super(message);
        }
    }
}
