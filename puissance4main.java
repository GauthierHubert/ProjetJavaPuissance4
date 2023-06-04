package puissance4;

import java.util.Scanner;
import java.util.Random;
//-----------------------------------------------------------------------------------------------------------
//| COMMENTAIRE <-- A REMPLACER
//-----------------------------------------------------------------------------------------------------------	

public class puissance4main {
	public static void main(String[] args) {
		// -----------------------------------------------------------------------------------------------------------
		// | Initialisation variable
		// -----------------------------------------------------------------------------------------------------------
		int bot, i, j, choi, cpt, cpt2, cptfin, t, ti, tj, pg, pp, pj, pe;
		char uwu = '_';
		choi = 0;
		ti = 0;
		tj = 0;
		cptfin = 0;
		cpt2 = 0;
		pg = 0;
		pp = 0;
		pj = 0;
		pe = 0;

		boolean fin = false;

		// -----------------------------------------------------------------------------------------------------------
		// | Menu et choix (pas finit)
		// -----------------------------------------------------------------------------------------------------------

		while (choi != 4) {
			choi = 0;
			while (choi < 1 || choi > 4) {
				menu();
				choi = new Scanner(System.in).nextInt();
				if (choi < 1 || choi > 4) {
					System.out.println("Veuillez choisir un choix valide");
				}
			}
			switch (choi) {

			case 1:
				pg = 0;
				pp = 0;
				pj = 0;
				pe = 0;
				break;

			case 2:
				// -----------------------------------------------------------------------------------------------------------
				// | Main et initialisation du jeu
				// -----------------------------------------------------------------------------------------------------------

				int tt = tt();
				char jeu[][] = setJeu();
				int vec[] = setVec();
				int vec2[] = setVec2();
				fin = false;
				cptfin = 0;
				// -----------------------------------------------------------------------------------------------------------
				// | Changement joueur
				// -----------------------------------------------------------------------------------------------------------

				while (fin == false && cptfin < 42) {
					if (tt % 2 == 0) {
						j = -1;
						// -----------------------------------------------------------------------------------------------------------
						// | Joueur 1 Choix Colonne
						// -----------------------------------------------------------------------------------------------------------
						while ((j >= 7 || j <= -1)) {
							j = -1;
							if (j == -1) {
								System.out.println("Choissisez une colonne");
								j = new Scanner(System.in).nextInt();
								j = j - 1;
							}
							if (j < 7 && j > -1)
								if (vec2[j] == 1) {
									j = -1;
								}
						}
						// -----------------------------------------------------------------------------------------------------------
						// | Changement dans le tableau main
						// -----------------------------------------------------------------------------------------------------------

						cpt = 0;
						i = jeu.length - 1;
						while (cpt == 0 && i >= 0) {
							if (jeu[i][j] == '_') {
								jeu[i][j] = 'J';
								cpt++;
							}
							i--;
						}
						// -----------------------------------------------------------------------------------------------------------
						// | Bot qui joue
						// -----------------------------------------------------------------------------------------------------------
					} else {
						jeu = bot(jeu, vec, vec2, tt);
					}
					// -----------------------------------------------------------------------------------------------------------
					// | Gestion du vecteur 1 et initialisation des variables pour la fin
					// -----------------------------------------------------------------------------------------------------------

					for (j = 0; j < vec.length && cpt2 == 0; j++) {
						if (vec2[j] == 0) {
							i = vec[j] - 1;
							if (jeu[i][j] != '_') {
								uwu = jeu[i][j];
								vec[j]--;
								cpt2++;
								ti = i;
								tj = j;
							}
						}
					}
					// -----------------------------------------------------------------------------------------------------------
					// | Gestion vecteur 2 pour le lock des colonnes
					// -----------------------------------------------------------------------------------------------------------
					for (j = 0; j < vec2.length; j++) {
						if (vec[j] == 0) {
							vec[j] = 1;
							vec2[j] = 1;
						}
					}
					cpt2 = 0;
					tt++;
					cptfin++;
					pj++;
					// -----------------------------------------------------------------------------------------------------------
					// | Affichage tableau
					// -----------------------------------------------------------------------------------------------------------
					for (i = 0; i < jeu.length; i++) {
						for (j = 0; j < jeu[0].length; j++) {
							if (jeu[i][j] == 'R') {
								System.out.print(jeu[i][j] + " ");
							} else {
								System.out.print(jeu[i][j] + " ");

							}
						}
						System.out.println();
					}

					System.out.println("\n1 2 3 4 5 6 7");
					// -----------------------------------------------------------------------------------------------------------
					// | Check fin du jeu
					// -----------------------------------------------------------------------------------------------------------
					fin = Fin(jeu, fin, ti, tj, uwu);
				}
				// -----------------------------------------------------------------------------------------------------------
				// | Affichage gagnant
				// -----------------------------------------------------------------------------------------------------------
				tt = tt - 1;
				if (tt % 2 == 0 && fin == true) {
					System.out.println("Vous avez gagné");
					pg++;
				} else if (fin == true) {
					System.out.println("Vous avez perdu");
					pp++;
				} else if (fin == false) {
					System.out.println("C'est une égalité!");
					pe++;
				}

				break;
			case 3:
				stat(pg, pp, pe, pj);
				break;

			case 4:
				System.out.println("Au Revoir ! Merci d'avoir joué");
				break;
			}
		}

	}

	public static void stat(int pg, int pp, int pe, int pj) {
		// -----------------------------------------------------------------------------------------------------------
		// | Affichage stat
		// -----------------------------------------------------------------------------------------------------------
		System.out.println();
		System.out.println("*************************************");
		System.out.println("* Partie Gagnée  : " + pg + "\t\t    *");
		System.out.println("* Partie Perdue  : " + pp + "\t\t    *");
		System.out.println("* Partie Egalité : " + pe + "\t\t    *");
		System.out.println("* Pièces Jouées  : " + pj + "\t\t    *");
		System.out.println("*************************************");
	}

	public static void menu() {
		// -----------------------------------------------------------------------------------------------------------
		// | Affichage menu
		// -----------------------------------------------------------------------------------------------------------
		System.out.println();
		System.out.println("*************************************");
		System.out.println("* 1.  Rénitialiser les statistiques *");
		System.out.println("* 2.             Jouer              *");
		System.out.println("* 3.    Afficher les statistiques   *");
		System.out.println("* 4.      Quitter le programme      *");
		System.out.println("*************************************");

	}

	public static char[][] setJeu() {
		char jeu[][] = new char[6][7];
		int i, j;
		// -----------------------------------------------------------------------------------------------------------
		// | Initialisation du jeu
		// -----------------------------------------------------------------------------------------------------------
		System.out.println();

		for (i = 0; i < jeu.length; i++) {
			for (j = 0; j < jeu[0].length; j++) {
				jeu[i][j] = '_';
				System.out.print(jeu[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("\n1 2 3 4 5 6 7\n");
		return jeu;
	}

	public static int tt() {
		int tt;
		char q;
		// -----------------------------------------------------------------------------------------------------------
		// | Choix du joueur aléatoire
		// -----------------------------------------------------------------------------------------------------------
		System.out.println("\nLa personne qui commence va être choisie aléatoirement");
		Random r = new Random();
		tt = r.nextInt(2) + 1;

		if (tt == 2) {
			System.out.println("Vous commencez !");
		} else {
			System.out.println("Vous jouez en second !");
		}
		System.out.println("Appuyez sur une touche pour commencer");
		q = new Scanner(System.in).nextLine().charAt(0);

		return tt;
	}

	public static char[][] bot(char[][] jeu, int[] vec, int[] vec2, int tt) {

		int row, col, t, i, j, cpt2, check;
		row = 0;
		col = 0;
		boolean fin = false;
		// -----------------------------------------------------------------------------------------------------------
		// | Main bot
		// -----------------------------------------------------------------------------------------------------------

		int cpt = 0;
		// -----------------------------------------------------------------------------------------------------------
		// | Premier coup
		// -----------------------------------------------------------------------------------------------------------

		if (tt == 1) {
			row = vec[col] - 1;
			jeu[row][col + 3] = 'R';
			cpt++;
		}

		// -----------------------------------------------------------------------------------------------------------
		// | Check win immédiat
		// -----------------------------------------------------------------------------------------------------------
		for (col = 0; col < vec.length && cpt == 0; col++) {
			row = vec[col] - 1;
			if (vec2[col] == 0) {
				if (row < 3 && cpt == 0) {
					if (jeu[row + 1][col] == 'R' && jeu[row + 2][col] == 'R' && jeu[row + 3][col] == 'R') {
						jeu[row][col] = 'R';
						cpt++;
					}
				}
				if (col < 4 && cpt == 0) {
					if (jeu[row][col + 1] == 'R' && jeu[row][col + 2] == 'R' && jeu[row][col + 3] == 'R') {
						jeu[row][col] = 'R';
						cpt++;
					}
				}
				if (col > 2 && cpt == 0) {
					if (jeu[row][col - 1] == 'R' && jeu[row][col - 2] == 'R' && jeu[row][col - 3] == 'R') {
						jeu[row][col] = 'R';
						cpt++;
					}
				}
				if (row < 3 && cpt == 0) {
					if (col > 2) {
						if (jeu[row + 1][col - 1] == 'R' && jeu[row + 2][col - 2] == 'R'
								&& jeu[row + 3][col - 3] == 'R') {
							jeu[row][col] = 'R';
							cpt++;
						}
					}
				}
				cpt2 = 0;
				check = 0;
				if (cpt == 0) {
					for (i = row, j = col; i < jeu.length && j < jeu[0].length && check == 0; i++, j++) {
						if (i == row && i < jeu.length - 1 && j < jeu[0].length - 1) {
							i++;
							j++;
						}
						if (jeu[i][j] == 'R') {
							cpt2++;
						} else {
							check = 1;
						}
					}
					check = 0;
					for (i = row, j = col; i > 0 && col >= 0 && check == 0; i--, j--) {
						if (i == row && i > 0 && j > 0) {
							i--;
							j--;
						}
						if (jeu[i][j] == 'R') {
							cpt2++;
						} else {
							check = 1;
						}
					}
					if (cpt2 >= 3) {
						jeu[row][col] = 'R';
						cpt++;
					}
				}
				cpt2 = 0;
				check = 0;
				if (cpt == 0) {
					for (i = row, j = col; i < jeu.length && j >= 0 && check == 0; i++, j--) {
						if (i == row && i < jeu.length - 1 && j > 0) {
							i++;
							j--;

						}
						if (jeu[i][j] == 'R') {
							cpt2++;
						} else {
							check = 1;
						}
					}
					check = 0;
					for (i = row, j = col; i >= 0 && col < jeu[0].length && check == 0; i--, j++) {
						if (i == row && i > 0 && j < jeu[0].length - 1) {
							i--;
							j++;
						}
						if (jeu[i][j] == 'R') {
							cpt2++;
						} else {
							check = 1;
						}
					}
					if (cpt2 >= 3) {
						jeu[row][col] = 'R';
						cpt++;
					}

				}

			}
		}
		// -----------------------------------------------------------------------------------------------------------
		// | Check danger immédiat
		// -----------------------------------------------------------------------------------------------------------
		for (col = 0; col < vec.length && cpt == 0; col++) {
			row = vec[col] - 1;

			if (vec2[col] == 0) {

				if (row < 3 && cpt == 0) {

					if (jeu[row + 1][col] == 'J' && jeu[row + 2][col] == 'J' && jeu[row + 3][col] == 'J') {
						jeu[row][col] = 'R';
						cpt++;

					}
				}
				i = row;
				cpt2 = 0;
				check = 0;
				if (cpt == 0) {
					for (j = col; j < jeu[0].length && check == 0; j++) {
						if (j == col && j < jeu[0].length - 1) {
							j++;

						}
						if (jeu[i][j] == 'J') {
							cpt2++;
						} else {
							check = 1;
						}
					}
					check = 0;
					for (j = col; j >= 0 && check == 0; j--) {
						if (j == col && j > 1) {
							j--;
						}
						if (jeu[i][j] == 'J') {
							cpt2++;
						} else {
							check = 1;
						}
					}
					if (cpt2 >= 3) {
						jeu[row][col] = 'R';
						cpt++;
					}

				}

				cpt2 = 0;
				check = 0;
				if (cpt == 0) {
					for (i = row, j = col; i < jeu.length && col < jeu[0].length && check == 0; i++, j++) {
						if (i == row && i < jeu.length - 1 && col < jeu[0].length - 1) {
							i++;
							j++;
						}
						if (jeu[i][j] == 'J') {
							cpt2++;
						} else {
							check = 1;
						}
					}
					check = 0;
					for (i = row, j = col; i >= 0 && j >= 0 && check <= 1; i--, j--) {
						if (i == row && i > 1 && j > 1) {
							i--;
							j--;
						}
						if (jeu[i][j] == 'J') {
							cpt2++;
						} else {
							check++;
						}
					}
					if (cpt2 >= 3) {
						jeu[row][col] = 'R';
						cpt++;
					}
				}
				cpt2 = 0;
				check = 0;
				if (cpt == 0) {
					for (i = row, j = col; i < jeu.length && col >= 0 && check == 0; i++, j--) {
						if (i == row && i < jeu.length - 1 && j > 1) {
							i++;
							j--;
						}
						if (jeu[i][j] == 'J') {
							cpt2++;
						} else {
							check = 1;
						}
					}
					check = 0;
					for (i = row, j = col; i >= 0 && col < jeu[0].length && check == 0; i--, j++) {
						if (i == row && i > 0 && j < jeu[0].length - 2) {
							i--;
							j++;
						}
						if (jeu[i][j] == 'J') {
							cpt2++;
						} else {
							check = 1;
						}
					}
					if (cpt2 >= 3) {
						jeu[row][col] = 'R';
						cpt++;
					}

				}

				if (col > 1 && cpt == 0) {
					if (col < 5) {
						if (jeu[row][col - 1] == 'J' && jeu[row][col + 1] == 'J' && jeu[row][col - 2] != 'R'
								&& jeu[row][col + 2] != 'R') {
							jeu[row][col] = 'R';
							cpt++;
						}
					}
				}

			}
		}
		// -----------------------------------------------------------------------------------------------------------
		// | Danger potentiel
		// -----------------------------------------------------------------------------------------------------------

		for (col = 0; col < vec.length && cpt == 0; col++) {
			row = vec[col] - 1;

			if (vec2[col] == 0) {

				if (row < 5 && col < 4 && cpt == 0) {

					if (jeu[row][col + 1] == 'J' && jeu[row][col + 2] == 'J' && jeu[row][col + 3] == '_'
							&& jeu[row + 1][col + 3] != '_') {
						jeu[row][col] = 'R';

						cpt++;
					}
				} else if (col < 4 && cpt == 0) {

					if (jeu[row][col + 1] == 'J' && jeu[row][col + 2] == 'J' && jeu[row][col + 3] == '_') {
						jeu[row][col] = 'R';

						cpt++;
					}
				}
				if (row < 5 && col > 2 && cpt == 0) {

					if (jeu[row][col - 1] == 'J' && jeu[row][col - 2] == 'J' && jeu[row][col - 3] != 'R'
							&& jeu[row + 1][col - 3] != '_') {
						jeu[row][col] = 'R';

						cpt++;
					}
				} else if (col > 2 && cpt == 0) {

					if (jeu[row][col - 1] == 'J' && jeu[row][col - 2] == 'J' && jeu[row][col - 3] != 'R') {
						jeu[row][col] = 'R';

						cpt++;
					}
				}

			}
		}
		// -----------------------------------------------------------------------------------------------------------
		// | Attaque avancée
		// -----------------------------------------------------------------------------------------------------------
		for (col = 0; col < vec.length && cpt == 0; col++) {
			row = vec[col] - 1;

			if (col > 2 && col < 6 && cpt == 0) {
				if (jeu[row][col - 1] == 'R' && jeu[row][col - 2] == 'R'
						&& (jeu[row][col - 3] == '_' || jeu[row][col + 1] == '_')) {
					jeu[row][col] = 'R';
					cpt++;
				}
			}
			if (col < 4 && col > 0 && cpt == 0) {
				if (jeu[row][col + 1] == 'R' && jeu[row][col + 2] == 'R'
						&& (jeu[row][col + 3] == '_' || jeu[row][col - 1] == '_')) {
					jeu[row][col] = 'R';
					cpt++;
				}
			}
			if (row < 4 && cpt == 0) {
				if (jeu[row + 1][col] == 'R' && jeu[row + 2][col] == 'R') {
					jeu[row][col] = 'R';
					cpt++;
				}
			}
			if (row < 3 && col > 2 && col < 6 && row > 0 && cpt == 0) {
				if (jeu[row + 1][col + 1] == 'R' && jeu[row + 2][col + 2] == 'R'
						&& (jeu[row + 3][col + 3] == '_' || jeu[row - 1][col - 1] == '_')) {
					jeu[row][col] = 'R';
					cpt++;
				}
			}
			if (row > 2 && col > 2 && col < 6 && row < 5 && cpt == 0) {
				if (jeu[row - 1][col + 1] == 'R' && jeu[row - 2][col + 2] == 'R'
						&& (jeu[row - 3][col + 3] == '_' || jeu[row + 1][col - 1] == '_')) {
					jeu[row][col] = 'R';
					cpt++;
				}
			}
			if (row < 3 && col > 3 && row > 0 && col < 6 && cpt == 0) {
				if (jeu[row + 1][col - 1] == 'R' && jeu[row + 2][col - 2] == 'R'
						&& (jeu[row + 3][col - 3] == '_' || jeu[row - 1][col + 1] == '_')) {
					jeu[row][col] = 'R';
					cpt++;
				}
			}

		}
		// -----------------------------------------------------------------------------------------------------------
		// | Mouvement basique
		// -----------------------------------------------------------------------------------------------------------
		for (col = 0; col < vec.length && cpt == 0; col++) {
			row = vec[col] - 1;

			if (row < 5 && row > 1 && row > 0 && col > 0 && col < 6 && cpt == 0) {
				if (jeu[row + 1][col] == 'R' && jeu[row - 1][col - 1] != 'J' && jeu[row - 1][col + 1] != 'J') {
					jeu[row][col] = 'R';
					cpt++;
				}
			}
			if (row > 0 && col > 0 && col < 6 && cpt == 0) {
				if ((jeu[row][col + 1] == 'R' || jeu[row][col - 1] == 'R') && jeu[row - 1][col - 1] != 'J'
						&& jeu[row - 1][col + 1] != 'J') {
					jeu[row][col] = 'R';
					cpt++;
				}
			} else if (col > 0 && col < 6 && cpt == 0) {
				if (jeu[row][col + 1] == 'R' || jeu[row][col - 1] == 'R') {
					jeu[row][col] = 'R';
					cpt++;
				}

			}
		}
		for (col = 0; col < vec.length && cpt == 0; col++) {
			row = vec[col] - 1;

			if (vec2[col] == 0) {
				if (col == 0 && cpt == 0) {
					if (jeu[row][col + 1] == 'J') {
						jeu[row][col] = 'R';
						cpt++;
					}
				}
				if (col == 6 && cpt == 0) {
					if (jeu[row][col - 1] == 'J') {
						jeu[row][col] = 'R';
						cpt++;
					}
				}
				if (col > 0 && col < 6 && cpt == 0) {
					if ((jeu[row][col + 1] == 'J' || jeu[row][col - 1] == 'J') && cpt == 0) {
						jeu[row][col] = 'R';
						cpt++;
					}
				}
			}
		}
		System.out.println(cpt);
		for (col = 0; col < vec.length && cpt == 0; col++) {
			row = vec[col] - 1;
			if (cpt == 0) {
				jeu[row][col] = 'R';
				cpt++;
				System.out.println("auto");
			}
		}

		return jeu;

	}

	public static int[] setVec() {
		// -----------------------------------------------------------------------------------------------------------
		// | Initialisation du vecteur
		// -----------------------------------------------------------------------------------------------------------
		int vec[] = new int[7];
		int i;
		for (i = 0; i < vec.length; i++) {
			vec[i] = 6;
		}
		return vec;
	}

	public static int[] setVec2() {
		// -----------------------------------------------------------------------------------------------------------
		// | Initialisation du vecteur2
		// -----------------------------------------------------------------------------------------------------------
		int vec2[] = new int[7];
		for (int j = 0; j > vec2.length; j++) {
			vec2[j] = 0;

		}

		return vec2;
	}

	public static boolean Fin(char[][] jeu, boolean fin, int row, int col, char pion) {
		int cpt = 0;
		int i;
		int j = col;
		boolean stop = false;
//	    -----------------------------------------------------------------------------------------------------------
//	    | Test vertical
//	    -----------------------------------------------------------------------------------------------------------	
		for (i = row; i < jeu.length && stop == false; i++) {
			if (pion == jeu[i][j]) {
				cpt++;

			} else {
				stop = true;
			}
		}
		if (cpt >= 4) {
			fin = true;
		}
		cpt = 0;
		stop = false;
		i = row;

//		    -----------------------------------------------------------------------------------------------------------
//		    | Test Horizontal
//		    -----------------------------------------------------------------------------------------------------------	
		for (j = col; j < jeu[0].length && stop == false; j++) {
			if (pion == jeu[i][j]) {
				cpt++;

			} else {
				stop = true;
			}
		}
		cpt = cpt - 1;
		stop = false;
		for (j = col; j >= 0 && stop == false; j--) {

			if (pion == jeu[i][j]) {
				cpt++;

			} else {
				stop = true;
			}
		}
		if (cpt >= 4) {
			fin = true;
		}

		cpt = 0;
		stop = false;
//		    -----------------------------------------------------------------------------------------------------------
//		    | Test Diagonale bas droite
//		    -----------------------------------------------------------------------------------------------------------	

		for (i = row, j = col; i < jeu.length && j < jeu[0].length && stop == false; i++, j++) {
			if (pion == jeu[i][j]) {
				cpt++;

			} else {
				stop = true;
			}
		}
		stop = false;
		cpt = cpt - 1;

		for (i = row, j = col; i >= 0 && j >= 0 && stop == false; i--, j--) {
			if (pion == jeu[i][j]) {
				cpt++;
			} else {
				stop = true;
			}
		}
		if (cpt >= 4) {
			fin = true;
		}
		stop = false;
		cpt = 0;

//		    -----------------------------------------------------------------------------------------------------------
//		    | Test diagonal haut droite
//		    -----------------------------------------------------------------------------------------------------------	
		for (i = row, j = col; i < jeu.length && j >= 0 && stop == false; i++, j--) {
			if (pion == jeu[i][j]) {
				cpt++;

			} else {
				stop = true;
			}
		}
		stop = false;
		cpt = cpt - 1;

		for (i = row, j = col; i >= 0 && j < jeu[0].length && stop == false; i--, j++) {
			if (pion == jeu[i][j]) {
				cpt++;
			} else {
				stop = true;
			}
		}
		if (cpt >= 4) {
			fin = true;
		}
		cpt = 0;

		return fin;
	}
}
