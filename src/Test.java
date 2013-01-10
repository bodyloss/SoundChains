import co.uk.bodyloss.soundchains.*;


public class Test {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		
		SoundChain sc = new SoundChain(44100);
		// add a sine wave generator at 8khz
		sc.setGenerator(Generators.Sine(8000, 44100))
		.addLink(new ChainLink() {
			private float decay = 1.0f;
			@Override
			public float processSample(float sample, long currentSample) {
				decay -= 0.00002f;
				if (decay < 0.0f) decay = 0.0f;
				return sample / decay;
			}
			
		});
		sc.playChain(3.0f);
	}

}
