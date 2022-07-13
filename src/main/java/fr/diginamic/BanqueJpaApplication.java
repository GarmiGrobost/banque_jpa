package fr.diginamic;

public class BanqueJpaApplication {

	public static void main(String[] args) {
		var listeComptes = EchangerAvecLaBaseDeDonnees.recupererTousLesComptes();
		
		System.out.println(listeComptes);
		
	}

}
