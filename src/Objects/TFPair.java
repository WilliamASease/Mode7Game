package Objects;

import Frames.FrameInstruction;
import Ticks.TickInstruction;

public class TFPair<T extends TickInstruction, F extends FrameInstruction>
{
	public T ti;
	public F fi;
	
	public TFPair(T tii, F fii)
	{
		ti = tii;
		fi = fii;
	}
}
