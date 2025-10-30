
class Vozilo {
    String proizvodjac;
    int godina;
    int kubikaza;
    String boja;


	public Vozilo(String proizvodjac, int godina, int kubikaza, String boja) {
        this.proizvodjac = proizvodjac;
        this.godina = godina;
        this.kubikaza = kubikaza;
        this.boja = boja;
    }

public void prikazi() {
    System.out.println("Proizvođač: " + proizvodjac);
    System.out.println("Godina: " + godina);
    System.out.println("Kubikaža: " + kubikaza);
    System.out.println("Boja: " + boja);
}

public double cijenaRegistracije() {
    double cijena = 100;

    if (godina < 2010) {
        cijena += 30;
    }

    if (kubikaza > 2000) {
        cijena += 50;
    }

    return cijena;
}
}

class Automobil extends Vozilo {
	int brojVrata;
	String tipGoriva;

	public Automobil(String proizvodjac, int godina, int kubikaza, String boja, int brojVrata, String tipGoriva) {
		super(proizvodjac, godina, kubikaza, boja);
		this.brojVrata = brojVrata;
		this.tipGoriva = tipGoriva;
	}
	public double cijenaRegistracije() {
		double cijena = super.cijenaRegistracije();

		if (tipGoriva.equalsIgnoreCase("dizel")) {
			cijena += 20;
		}

		return cijena;
	}

	@Override
	public void prikaziInfo() {
		super.prikazi();
		System.out.println("Broj vrata: " + brojVrata);
		System.out.println("Tip goriva: " + tipGoriva);
	}
}

class Kamion extends Vozilo {
	int nosivost;
	boolean imaPrikolica;

	public Kamion(String proizvodjac, int godina, int kubikaza, String boja, int nosivost, boolean imaPrikolica) {
		super(proizvodjac, godina, kubikaza, boja);
		this.nosivost = nosivost;
		this.imaPrikolica = imaPrikolica;}
		public double cijenaRegistracije() {
			double cijena = super.cijenaRegistracije();
			if (prikolica) {
				cijena += 50;
			}
		
	    return cijena;
	    }
	

	@Override
	public void prikaziInfo() {
		super.prikazi();
		System.out.println("Nosivost: " + nosivost + " kg");
		System.out.println("Ima prikolicu: " + (imaPrikolica ? "Da" : "Ne"));
		
	}
}

class Kombi extends Vozilo {
	int brojSjedista;

	public Kombi(String proizvodjac, int godina, int kubikaza, String boja, int brojSjedista) {
		super(proizvodjac, godina, kubikaza, boja);
		this.brojSjedista = brojSjedista;
	}
	public double cijenaRegistracije() {
		double cijena = super.cijenaRegistracije();
		if (brojSjedista > 8) {
			cijena += 30;
		}

		return cijena;
	}
	

	@Override
	public void prikaziInfo() {
		super.prikazi();
		System.out.println("Broj sjedista: " + brojSjedista);
	}
}


public class Main {
    public static void main(String[] args) {
        Automobil auto = new Automobil("BMW", 2008, 2200, "Crna", 5, "dizel");
        Kamion kamion = new Kamion("Volvo", 2015, 5000, "Bijela", 12000, true);
        Kombi kombi = new Kombi("Mercedes", 2012, 1800, "Siva", 9);

        auto.prikaziInfo();
        System.out.println("Cijena registracije: " + auto.cijenaRegistracije() + " EUR\n");

        kamion.prikaziInfo();
        System.out.println("Cijena registracije: " + kamion.cijenaRegistracije() + " EUR\n");

        kombi.prikaziInfo();
        System.out.println("Cijena registracije: " + kombi.cijenaRegistracije() + " EUR\n");
    }
}


	