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

	public Village(String nom, int nbVillageoisMaximum) {
		this.nom = nom;
        this.nbEtals = nbEtals;
		villageois = new Gaulois[nbVillageoisMaximum];
        for (int i = 0; i < nbEtals; i++) {
            etals[i] = new Etal();
	    }
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

	public String afficherVillageois() {
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
	
	public void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit) {
        if (indiceEtal >= 0 && indiceEtal < etals.length) {
            etals[indiceEtal].occuperEtal(vendeur, produit, nbProduit);
        
        } else {
            System.out.println("Indice d'étal invalide.");
        }
    }
	
	public int trouverEtalLibre() {
        for (int i = 0; i < etals.length; i++) {
            if (!etals[i].isEtalOccupe()) {
                return i;
            }
        }
        return -1; 
        
        
    }
	public Etal[] trouverEtals(String produit) {
         int count = 0;
         for (Etal etal : etals) {
             if (etal.contientProduit(produit)) {
                 count++;
             }
         }
         Etal[] etalsAvecProduit = new Etal[count];
         int index = 0;
         for (Etal etal : etals) {
             if (etal.contientProduit(produit)) {
                 etalsAvecProduit[index] = etal;
                 index++;
             }
         }

         return etalsAvecProduit;
     }
	
	 public Etal trouverVendeur(Gaulois gaulois) {
         for (Etal etal : etals) {
             if (etal.isEtalOccupe() && etal.getVendeur() == gaulois) {
                 return etal;
             }
         }
         return null;
     }

	 public String afficherMarche() {
		    StringBuilder chaine = new StringBuilder();
		    boolean auMoinsUnEtalOccupe = false;

		    for (Etal etal : marche.etals) {
		        if (etal.isEtalOccupe()) {
		            chaine.append(etal.afficherEtal()).append("\n"); 
		            auMoinsUnEtalOccupe = true;
		        }
		    }

		    if (!auMoinsUnEtalOccupe) {
		        chaine.append("Aucun étal occupé sur le marché.\n");
		    }

		    if (marche.nbEtalVide > 0) {
		        chaine.append("Il reste " + marche.nbEtalVide + " étal(s) non utilisé(s) dans le marché.\n");
		    }

		    return chaine.toString();
		}

}








	
	
