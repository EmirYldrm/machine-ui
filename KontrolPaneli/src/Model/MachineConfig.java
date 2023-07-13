package Model;



public class MachineConfig {
	
	public static final int kalipMaxMM = 135;
	public static final int enjksiyonMaxMM = 145;
	public static final int milHatve = 5;
	public static final float enjeksiyonKasnakOrani = 2.66f;
	public static final float helezonKasnakOran = 4.0f;
	public static final float kalipKasnakOran = 7.23f;
	public static final float StepPerMM = 409.6f;
	public static final long stepPerRev  = 400;
	
	public static long calculateInjectionStep(float value) {
		return 	(long) enjeksiyonKasnakOrani * stepPerRev / milHatve;
		
	}
	public static long calculateMoldStep(float value) {
		return 	(long) kalipKasnakOran * stepPerRev / milHatve;
		
	}	

}
