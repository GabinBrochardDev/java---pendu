package fr.gabinbrochard.pendu.application.sauv;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Pendu {

	private static Scanner sc = new Scanner(System.in);

	// ATTRIBUTS D'INSTANCE
	private char[] motATrouver;
	private char[] lettreEsseyer;
	private String lettresEsseyes = "";
	private char[] reponceJoueur;
	private int nbTentativeErr;
	private int nbTentative;
	private int nombreCaracMot;
	private boolean fini = false;

	// ATTRIBUTS DE CLASSE
	private static final int NB_TENTATIVE_MAX = 9;

	// METHODE D'INSTANCE
	public void jeu() {

		afficherDessin();
		while (!isFini()) {
			String saisieUser;
			System.out.print("Saisi une lettre : ");
			saisieUser = sc.next();
			saisieUser = saisieUser.toUpperCase();
			if (saisieUser.matches("[a-zA-Z]") && saisieUser.length() == 1) {
				tentative(saisieUser.charAt(0));
			} else {
				System.out.println("La saisie est incorect, ne saisir qu'une seule lettre.");
			}
		}

	}

	public void choisirMot()  {


		// Tableau de mots
		String[] ListeMots = { "chat", "chien", "maison", "ordinateur", "fleur", "soleil", "banane", "montagne",
				"musique", "plage" };

		// Génère un nombre aléatoire de max 9
		Random r = new Random();
		int numMot;
		numMot = r.nextInt(ListeMots.length - 1);

		// Sélection du mot
		char[] mot = new char[0];
		mot = ListeMots[numMot].toUpperCase().toCharArray();
		setMotATrouver(mot);
		setNombreCaracMot(mot.length);

		reponceJoueur = new char[nombreCaracMot]; // Initialisez le tableau reponceJoueur avec la taille appropriée

		for (int i = 0; i < getNombreCaracMot(); i++) {
			reponceJoueur[i] = '_';
		}

		// System.out.println(Arrays.toString(mot));
	}

	public void tentative(char lettre) {
		// si la lettre proposé est présente alors ok sinon -1 a nombre de tentative
		// a la fin de cette méthode, nbTentativeErr >= 9 alors, fin de partie
		setNbTentative(getNbTentative() + 1);
		if (getLettreEsseyer() == null) {
			lettreEsseyer = new char[1];
		} else {
			lettreEsseyer = new char[lettreEsseyer.length + 1];
		}
		lettreEsseyer[getNbTentative() - 1] = lettre;
		if (lettresEsseyes == "") {
			lettresEsseyes = lettresEsseyes + lettre;
		} else {
			lettresEsseyes = lettresEsseyes + " - " + lettre;			
		}
		boolean existe = false;
		int nbLettreRestante = 0;
		for (int i = 0; i < getNombreCaracMot(); i++) {
			if (motATrouver[i] == lettre) {
				existe = true;
				reponceJoueur[i] = lettre;
			} else if (motATrouver[i] == '_') {
				nbLettreRestante++;
			}
		}

		if (existe) {
			System.out.println("La lettre " + lettre + " est bien présente dans le mot !\nIl te reste "
					+ nbLettreRestante + " lettre à trouver !");
			afficherDessin();

			if (Arrays.equals(motATrouver, reponceJoueur)) {
				finParti();
			}

		} else {
			setNbTentativeErr(getNbTentativeErr() + 1);
//			boolean dejaEsseyee = false;
//			for (char maLettre  : lettreEsseyer) {
//				if (maLettre == lettre) {
//					dejaEsseyee = true;
//				}
//			}
//			if(dejaEsseyee) {
//				System.err.println("Tu à déjà saisie cette lettre.");
//			} else {
//				System.err.println("Cette lettre n'est pas présente dans le mot.");				
//			}
			System.err.println("Cette lettre n'est pas présente dans le mot.");
			afficherDessin();
		}

	}

	public void afficherDessin() {
		if (getLettreEsseyer() != null) {

			
			System.out.println("Lettre esseyée.s : " + lettresEsseyes + "\n\n");
		}

		String motEnCours = "";
		for (char maLettre : reponceJoueur) {
			motEnCours = motEnCours + maLettre + " ";
		}
		System.out.println("Mot en cours : " + motEnCours + "\n\n");

		switch (getNbTentativeErr()) {
		case 1:
			System.out.println("-----------------\r\n" + "|  		|\r\n" + "|  	 	|\r\n" + "|  	 	|\r\n"
					+ "|  		|\r\n" + "|  	 	|\r\n" + "|  		|\r\n" + "| __________	|\r\n" + "|		|\r\n"
					+ "-----------------");
			break;
		case 2:
			System.out.println("-----------------\r\n" + "|  		|\r\n" + "|  |	 	|\r\n" + "|  |	 	|\r\n"
					+ "|  |		|\r\n" + "|  |		|\r\n" + "|  |		|\r\n" + "| _|________	|\r\n" + "|		|\r\n"
					+ "-----------------");
			break;
		case 3:
			System.out.println("-----------------\r\n" + "|  _________	|\r\n" + "|  |	 	|\r\n" + "|  |	 	|\r\n"
					+ "|  |		|\r\n" + "|  |		|\r\n" + "|  |		|\r\n" + "| _|________	|\r\n" + "|		|\r\n"
					+ "-----------------");
			break;
		case 4:
			System.out.println("-----------------\r\n" + "|  _________	|\r\n" + "|  |/	 	|\r\n" + "|  |	 	|\r\n"
					+ "|  |		|\r\n" + "|  |		|\r\n" + "|  |		|\r\n" + "| _|________	|\r\n" + "|		|\r\n"
					+ "-----------------");
			break;
		case 5:
			System.out.println("-----------------\r\n" + "|  _________	|\r\n" + "|  |/	 |	|\r\n" + "|  |	 	|\r\n"
					+ "|  |		|\r\n" + "|  |		|\r\n" + "|  |		|\r\n" + "| _|________	|\r\n" + "|		|\r\n"
					+ "-----------------");
			break;
		case 6:
			System.out.println("-----------------\r\n" + "|  _________	|\r\n" + "|  |/	 |	|\r\n" + "|  |	 O	|\r\n"
					+ "|  |		|\r\n" + "|  |		|\r\n" + "|  |		|\r\n" + "| _|________	|\r\n" + "|		|\r\n"
					+ "-----------------");
			break;
		case 7:
			System.out.println("-----------------\r\n" + "|  _________	|\r\n" + "|  |/	 |	|\r\n" + "|  |	 O	|\r\n"
					+ "|  |	 |	|\r\n" + "|  |	 	|\r\n" + "|  |		|\r\n" + "| _|________	|\r\n" + "|		|\r\n"
					+ "-----------------");
			break;
		case 8:
			System.out.println("-----------------\r\n" + "|  _________	|\r\n" + "|  |/	 |	|\r\n" + "|  |	 O	|\r\n"
					+ "|  |	-|-	|\r\n" + "|  |		|\r\n" + "|  |		|\r\n" + "| _|________	|\r\n" + "|		|\r\n"
					+ "-----------------");
			break;
		case 9:
			System.out.println("-----------------\r\n" + "|  _________	|\r\n" + "|  |/	 |	|\r\n" + "|  |	 O	|\r\n"
					+ "|  |	-|-	|\r\n" + "|  |	/ \\	|\r\n" + "|  |		|\r\n" + "| _|________	|\r\n"
					+ "|		|\r\n" + "-----------------");
		}
	}

	public void finParti() {
		if (getNbTentativeErr() < 9) {
			// GAGNÉ
			System.out.println("Félicitation tu à trouvé le mot en " + getNbTentative() + " !");
			setFini(true);
		}
	}

	// ***** CONSTRUCTEUR *****
	/**
	 * Constructeur par defaut Le constructure éxécute les métthodes servant à
	 * préparé la partie
	 */
	public Pendu() {
		choisirMot();
		System.out.println("Début de la partie !");
	}

	/**
	 * @return the motATrouver
	 */
	public char[] getMotATrouver() {
		return motATrouver;
	}

	/**
	 * @param motATrouver the motATrouver to set
	 */
	public void setMotATrouver(char[] motATrouver) {
		this.motATrouver = motATrouver;
	}

	/**
	 * @return the lettreEsseyer
	 */
	public char[] getLettreEsseyer() {
		return lettreEsseyer;
	}

	/**
	 * @param lettreEsseyer the lettreEsseyer to set
	 */
	public void setLettreEsseyer(char[] lettreEsseyer) {
		this.lettreEsseyer = lettreEsseyer;
	}

	/**
	 * @return the nbTentativeMax
	 */
	public static int getNbTentativeMax() {
		return NB_TENTATIVE_MAX;
	}

	/**
	 * @return the nombreCaracMot
	 */
	public int getNombreCaracMot() {
		return nombreCaracMot;
	}

	/**
	 * @param nombreCaracMot the nombreCaracMot to set
	 */
	public void setNombreCaracMot(int nombreCaracMot) {
		this.nombreCaracMot = nombreCaracMot;
	}

	/**
	 * @return the reponceJoueur
	 */
	public char[] getReponceJoueur() {
		return reponceJoueur;
	}

	/**
	 * @param reponceJoueur the reponceJoueur to set
	 */
	public void setReponceJoueur(char[] reponceJoueur) {
		this.reponceJoueur = reponceJoueur;
	}

	/**
	 * @return the nbTentativeErr
	 */
	public int getNbTentativeErr() {
		return nbTentativeErr;
	}

	/**
	 * @param nbTentativeErr the nbTentativeErr to set
	 */
	public void setNbTentativeErr(int nbTentativeErr) {
		this.nbTentativeErr = nbTentativeErr;
	}

	/**
	 * @return the fini
	 */
	public boolean isFini() {
		return fini;
	}

	/**
	 * @param fini the fini to set
	 */
	public void setFini(boolean fini) {
		this.fini = fini;
	}

	/**
	 * @return the nbTentative
	 */
	public int getNbTentative() {
		return nbTentative;
	}

	/**
	 * @param nbTentative the nbTentative to set
	 */
	public void setNbTentative(int nbTentative) {
		this.nbTentative = nbTentative;
	}

}
