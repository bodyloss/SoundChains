import co.uk.bodyloss.soundchains.*;
import co.uk.bodyloss.soundchains.links.*;


public class Test {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		
		SoundChain sc = new SoundChain(44100);
		sc.setGenerator(Generators.Square(500))
		.addLink(new ChainLink() {
			@Override
			public float processSample(float sample, long currentSample) {
				// play for 1/8th of a second then stop
				if (currentSample < sampleRate / 12 || (currentSample > sampleRate / 8 && currentSample < sampleRate / 2) )
					return sample;
				return 0;
			}
		})
		.addLink(new Delay(0.6f, 500.0f));
//		sc.playChain(7.0f);
		
		SoundChain sc2 = new SoundChain(44100)
		.setGenerator(
			new MultiGenerator().add(Generators.Sine(500))
			.add(Generators.Sine(2000))
			.add(Generators.Square(4000))
		);
//		sc2.playChain(1.0f);
		
		SoundChain sc3 = new SoundChain(44100);
		sc2.setGenerator(Generators.Sine(1000)).addLink(new ChainLink() {
			float heldSample = 0;
			int samplesToHold = 50;

			@Override
			public float processSample(float sample, long currentSample) {
				if (currentSample % samplesToHold == 0)
					heldSample = sample;
				
				return heldSample;
			}
		});
//		sc3.playChain(5.0f);
		
		SoundChain sc4 = new SoundChain(44100)
		.setGenerator(Generators.Sawtooth(240));
//		sc4.playChain(1.0f);
	}

}
