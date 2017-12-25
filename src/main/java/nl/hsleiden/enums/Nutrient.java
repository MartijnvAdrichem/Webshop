package nl.hsleiden.enums;

/**
 * The nutrients a product has
 */
public enum Nutrient {
	CALORIEEN("Caloriën", "kcal"),
	JOULE("Joule", "J"),
	STIKSTOF("Stikstof", "g"),
	EIWITTENTOTAAL("Eiwit", "g"),
	EIWITPLANT("E plant.", "g"),
	EIWITDIER("E dierl.", "g"),
	VETTEN("Vet", "g"),
	VERZADiGDVET("Vet verz.", "g"),
	LINOLZUUR("Linolzuur", "g"),
	TRANSVET("Transvet", "g"),
	ALA("ALA", "g"),
	EPA("EPA", "g"),
	DHA("DHA", "g"),
	CHOLESTEROL("Cholesterol", "mg"),
	KOOLHYDRATEN("Koolhydraten", "g"),
	SUIKER("Suiker", "g"),
	VEZELS("Vezels", "g"),
	WATER("Water", "g"),
	ALCOHOL("Alcohol", "g"),
	CALCIUM("Calcium", "mg"),
	FOSFOR("Fosfor", "mg"),
	IJZERT("Ijzer T", "mg"),
	IJZER_HAEM("Ijzer haem", "mg"),
	IJZER_NONHAEM("Ijzer nonHaem", "mg"),
	NATRIUM("Natrium", "mg"),
	KALIUM("Kalium", "mg"),
	MAGNESIUM("Magnesium", "mg"),
	ZINK("Zink", "mg"),
	SELENIUM("Selenium", "µg"),
	KOPER("Koper", "mg"),
	JODIUM("Jodium", "µg"),
	RETINOL("Retinol", "µg"),
	B_CARTEEN("B carteen", "µg"),
	VIT_B1("Vit. B1", "mg"),
	VIT_B2("Vit. B2", "mg"),
	VIT_B6("Vit. B6", "mg"),
	VIT_B12("Vit. B12", "µg"),
	VIT_D("Vit. D", "µg"),
	VIT_E("Vit. E", "µg"),
	VIT_C("Vit. C", "mg"),
	FOLAAT("Folaat", "µg"),
	FOLIUMZUUR("Foliumzuur", "µg"),
	NIC_ZUUR("Nic. Zuur", "mg"),
	A_TOCOFEROL("A tocoferol", "mg"),
	B_TOCOFEROL("B tocoferol", "mg"),
	GAMMA_TOCOFEROL("Gamma Tocoferol", "mg"),
	D_TOCOFEROL("D Tocoferol", "mg"),
	A_CAROTEEN("A caroteen", "µg"),
	LUTEINE("Luteïne", "µg"),
	ZEAXANTHINE("Zeaxanthine", "µg"),
	B_CRYPTOXANTHINE("B cryptoxanthine", "µg"),
	LYCOPEEN("Lycopeen", "µg"),
	VIT_K("Vit. K", "µg"),
	VIT_K1("Vit. K1", "µg"),
	VIT_K2("Vit. K2", "µg"),
	CHOLECALCIFEROL("Cholecaliferol", "µg"),

	POLYSACHARIDEN("Polysachariden", "g"),
	EVONVERZADIGDVET("E.O.V", "g"),
	MVONVERZADIGDVET("M.O.V", "g"),
	OMEGA3("Omega 3", "g"),
	OMEGA6("Omega 6", "g");

	private String name;
	private String measurementUnit;

	Nutrient(String name, String measurementUnit){
		this.name = name;
		this.measurementUnit = measurementUnit;
	}

	public String getName() {
		return name;
	}

	public String getMeasurementUnit() {
		return measurementUnit;
	}

	@Override
	public String toString() {
		return getName() + " (" + getMeasurementUnit() + ")";
	}
}
