package predavanje;

import java.util.ArrayList;

class Auto {
	private String markaAuta;
	private int godisteAuta;
	private float snagaMotora;
	private boolean prodato;
	private float kubikaza;
	private boolean registrovan;
	private static int brojProdatihAuta = 0;

	public Auto(String markaAuta, int godisteAuta, float snagaMotora, boolean prodato, float kubikaza,boolean registrovan) {
		this.markaAuta = markaAuta;
		this.godisteAuta = godisteAuta;
		this.snagaMotora = snagaMotora;
		this.kubikaza = kubikaza;
		this.prodato = prodato;
		this.registrovan = false;
		if (prodato) brojProdatihAuta++;
        this.registrovan = godisteAuta <  1985 ? false: registrovan;
        
        
    }
	public String getMarkaAuta() {
		return markaAuta;
	}
	public void setMarkaAuta(String markaAuta) {
		this.markaAuta = markaAuta;
	}
	public int getGodisteAuta() {
		return godisteAuta;
	}
	public void setGodisteAuta(int godisteAuta) {
		if (godisteAuta >= 2025) {
			System.out.println("Nevalidno godiste auta.");
		}
	}
		public float getSnagaMotora() {
			return this.snagaMotora;
		}
		public void setSnagaMotora(float snagaMotora) {
			this.snagaMotora = snagaMotora;
		}
		public boolean isProdato() {
			return this.prodato;
			
		}
		public void setProdato(boolean prodato) {
			if (this.prodato == false && prodato == true) {
				brojProdatihAuta++;
			}
			this.prodato = prodato;
		}
		public double getKubikaza() {
			return this.kubikaza;
		}
		public void setKubikaza(float kubikaza) {
			this.kubikaza = kubikaza;
		}
		public boolean isRegistrovan() {
			return this.registrovan;
		}
		public void setRegistrovan (boolean registrovan) {
			if (this.godisteAuta < 1985) {
				this.registrovan = false;
			} else {
				this.registrovan = registrovan;
			}
		}
		public static int getBrojProdatihAuta() {
			return brojProdatihAuta;
		}
		public static double koeficijijentPoGodistu(int godisteAuta) {
			if (godisteAuta < 1985) return 0.0;
			if(godisteAuta<=2000) return 3.0;
			if (godisteAuta <= 2010)return 2.0;
			else
				return 1.5;
			}
			public void cenaAuta() {
			
		}
		public double cenaRegistracije() {
                return koeficijijentPoGodistu(godisteAuta) * kubikaza*snagaMotora;
            }
		@Override
		public String toString() {
			return String.format("(Auto [marka-8s, godiste-ed, snaga=%.If, \" + kubikaza=8. If, registrovan=85, prodatoes, cijenaReg=%,2f]\"\r\n"
					+ ", markaAuta, godisteduta, snagaMotora, kubikaza,\r\n"
					+ "registrovan? \"da\": \"ne\"\r\n"
					+ ", prodato? \"da\": \"ne\", cenaRegistracije());");
					
			
		}
		
    }
class AutoServis {
	public static ArrayList<Auto> neregistrovaniKojiSeMoguRegistrovati(ArrayList<Auto> auta) {
		ArrayList<Auto> rezultat = new ArrayList<>();
		for (Auto a : auta) {
			if (!a.isRegistrovan() && a.getGodisteAuta() >= 1985) {
				rezultat.add(a);
			}
		}
		return rezultat;
	}

	public static double ukupnaCenaRegistracije(ArrayList<Auto> auta) {
		double ukupnaCena = 0.0;
		for (Auto a : auta) {
			ukupnaCena += a.cenaRegistracije();
		}
		return ukupnaCena;
	}
}
	

public class TestAuto {
	public static void main(String[] args) {
		ArrayList<Auto> auta = new ArrayList<>();
		Auto a1 = new Auto("Audi", 2015, 150.0f, false, 2000.0f,true);
		Auto a2 = new Auto("BMW", 1995, 120.0f, true, 1800.0f,false);
		System.out.println(a1);
	}
} 
