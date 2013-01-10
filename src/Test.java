import co.uk.bodyloss.soundchains.*;
import co.uk.bodyloss.soundchains.links.*;


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
			@Override
			public float processSample(float sample, long currentSample) {
				if ( currentSample > sampleRate / 8)
					return 0;
				return sample;
			}
		})
		.addLink(new Delay(0.1f, 500.0f));
		sc.playChain(7.0f);
		
//		SoundChain sc2 = new SoundChain(44100);
//		sc2.setGenerator(Generators.Sine(1000, 44100)).addLink(new ChainLink() {
//			float heldSample = 0;
//			int samplesToHold = 50;
//
//			@Override
//			public float processSample(float sample, long currentSample) {
//				if (currentSample % samplesToHold == 0)
//					heldSample = sample;
//				
//				return heldSample;
//			}
//		}).playChain(5.0f);
	}

}
