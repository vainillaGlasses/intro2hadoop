package hadoop;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.WritableComparable;


public class VariableStation implements WritableComparable {
     
      private Text stationT;
      private FloatWritable valueT;
      
      private Text stationTM;
      private FloatWritable valueTM;
      
      private Text stationTm;
      private FloatWritable valueTm;
      
      private Text stationPP;
      private FloatWritable valuePP;
      
      private Text stationV;
      private FloatWritable valueV;
      
      private Text stationRA;
      private FloatWritable valueRA;
      
      private Text stationSN;
      private FloatWritable valueSN;
      
      private Text stationTS;
      private FloatWritable valueTS;
      
      private Text stationFG;
      private FloatWritable valueFG;
      
      private Text stationTN;
      private FloatWritable valueTN;
      
      private Text stationGR;
      private FloatWritable valueGR;
      
      
      public VariableStation() {             
            super();
     		this.stationT = new Text();
     		this.valueT = new FloatWritable();
     		this.stationTM = new Text();
     		this.valueTM = new FloatWritable();
     		this.stationTm = new Text();
     		this.valueTm = new FloatWritable();
     		this.stationPP = new Text();
     		this.valuePP = new FloatWritable();
     		this.stationV = new Text();
     		this.valueV = new FloatWritable();
     		this.stationRA = new Text();
     		this.valueRA = new FloatWritable();
     		this.stationSN = new Text();
     		this.valueSN = new FloatWritable();
     		this.stationTS = new Text();
     		this.valueTS = new FloatWritable();
     		this.stationFG = new Text();
     		this.valueFG = new FloatWritable();
     		this.stationTN = new Text();
     		this.valueTN = new FloatWritable();
     		this.stationGR = new Text();
     		this.valueGR = new FloatWritable();
      }
     
      
      
      public VariableStation(Text stationT, FloatWritable valueT, Text stationTM, FloatWritable valueTM,
			Text stationTm2, FloatWritable valueTm2, Text stationPP, FloatWritable valuePP, Text stationV,
			FloatWritable valueV, Text stationRA, FloatWritable valueRA, Text stationSN, FloatWritable valueSN,
			Text stationTS, FloatWritable valueTS, Text stationFG, FloatWritable valueFG, Text stationTN,
			FloatWritable valueTN, Text stationGR, FloatWritable valueGR) {
		super();
		this.stationT = stationT;
		this.valueT = valueT;
		this.stationTM = stationTM;
		this.valueTM = valueTM;
		this.stationTm = stationTm2;
		this.valueTm = valueTm2;
		this.stationPP = stationPP;
		this.valuePP = valuePP;
		this.stationV = stationV;
		this.valueV = valueV;
		this.stationRA = stationRA;
		this.valueRA = valueRA;
		this.stationSN = stationSN;
		this.valueSN = valueSN;
		this.stationTS = stationTS;
		this.valueTS = valueTS;
		this.stationFG = stationFG;
		this.valueFG = valueFG;
		this.stationTN = stationTN;
		this.valueTN = valueTN;
		this.stationGR = stationGR;
		this.valueGR = valueGR;
	}



	@Override
      public void readFields(DataInput in) throws IOException {
			this.stationT.readFields(in);
	  		this.valueT.readFields(in);
	  		this.stationTM.readFields(in);
	  		this.valueTM.readFields(in);
	  		this.stationTm.readFields(in);
	  		this.valueTm.readFields(in);
	  		this.stationPP.readFields(in);
	  		this.valuePP.readFields(in);
	  		this.stationV.readFields(in);
	  		this.valueV.readFields(in);
	  		this.stationRA.readFields(in);
	  		this.valueRA.readFields(in);
	  		this.stationSN.readFields(in);
	  		this.valueSN.readFields(in);
	  		this.stationTS.readFields(in);
	  		this.valueTS.readFields(in);
	  		this.stationFG.readFields(in);
	  		this.valueFG.readFields(in);
	  		this.stationTN.readFields(in);
	  		this.valueTN.readFields(in);
	  		this.stationGR.readFields(in);
	  		this.valueGR.readFields(in);
      }

      @Override
      public void write(DataOutput out) throws IOException {
    	  	this.stationT.write(out);
	  		this.valueT.write(out);
	  		this.stationTM.write(out);
	  		this.valueTM.write(out);
	  		this.stationTm.write(out);
	  		this.valueTm.write(out);
	  		this.stationPP.write(out);
	  		this.valuePP.write(out);
	  		this.stationV.write(out);
	  		this.valueV.write(out);
	  		this.stationRA.write(out);
	  		this.valueRA.write(out);
	  		this.stationSN.write(out);
	  		this.valueSN.write(out);
	  		this.stationTS.write(out);
	  		this.valueTS.write(out);
	  		this.stationFG.write(out);
	  		this.valueFG.write(out);
	  		this.stationTN.write(out);
	  		this.valueTN.write(out);
	  		this.stationGR.write(out);
	  		this.valueGR.write(out);
      }

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	@Override
	public String toString() {
		return stationT + ";" + valueT + "\t" + stationTM + ";" + valueTM + 
				"\t" + stationTm + ";" + valueTm + "\t" + stationPP + ";" + valuePP + 
				"\t" + stationV + ";" + valueV + "\t" + stationRA + ";" + valueRA + 
				"\t" + stationSN + ";" + valueSN + "\t" + stationTS + ";" + valueTS + 
				"\t" + stationFG + ";" + valueFG + "\t" + stationTN + ";" + valueTN + 
				"\t" + stationGR + ";" + valueGR; 
	}



	public Text getStationT() {
		return stationT;
	}

	public void setStationT(Text stationT) {
		this.stationT = stationT;
	}

	public FloatWritable getValueT() {
		return valueT;
	}

	public void setValueT(FloatWritable valueT) {
		this.valueT = valueT;
	}

	public Text getStationTM() {
		return stationTM;
	}

	public void setStationTM(Text stationTM) {
		this.stationTM = stationTM;
	}

	public FloatWritable getValueTM() {
		return valueTM;
	}

	public void setValueTM(FloatWritable valueTM) {
		this.valueTM = valueTM;
	}

	public Text getStationTm() {
		return stationTm;
	}

	public void setStationTm(Text stationTm) {
		this.stationTm = stationTm;
	}

	public FloatWritable getValueTm() {
		return valueTm;
	}

	public void setValueTm(FloatWritable valueTm) {
		this.valueTm = valueTm;
	}

	public Text getStationPP() {
		return stationPP;
	}

	public void setStationPP(Text stationPP) {
		this.stationPP = stationPP;
	}

	public FloatWritable getValuePP() {
		return valuePP;
	}

	public void setValuePP(FloatWritable valuePP) {
		this.valuePP = valuePP;
	}

	public Text getStationV() {
		return stationV;
	}

	public void setStationV(Text stationV) {
		this.stationV = stationV;
	}

	public FloatWritable getValueV() {
		return valueV;
	}

	public void setValueV(FloatWritable valueV) {
		this.valueV = valueV;
	}

	public Text getStationRA() {
		return stationRA;
	}

	public void setStationRA(Text stationRA) {
		this.stationRA = stationRA;
	}

	public FloatWritable getValueRA() {
		return valueRA;
	}

	public void setValueRA(FloatWritable valueRA) {
		this.valueRA = valueRA;
	}

	public Text getStationSN() {
		return stationSN;
	}

	public void setStationSN(Text stationSN) {
		this.stationSN = stationSN;
	}

	public FloatWritable getValueSN() {
		return valueSN;
	}

	public void setValueSN(FloatWritable valueSN) {
		this.valueSN = valueSN;
	}

	public Text getStationTS() {
		return stationTS;
	}

	public void setStationTS(Text stationTS) {
		this.stationTS = stationTS;
	}

	public FloatWritable getValueTS() {
		return valueTS;
	}

	public void setValueTS(FloatWritable valueTS) {
		this.valueTS = valueTS;
	}

	public Text getStationFG() {
		return stationFG;
	}

	public void setStationFG(Text stationFG) {
		this.stationFG = stationFG;
	}

	public FloatWritable getValueFG() {
		return valueFG;
	}

	public void setValueFG(FloatWritable valueFG) {
		this.valueFG = valueFG;
	}

	public Text getStationTN() {
		return stationTN;
	}

	public void setStationTN(Text stationTN) {
		this.stationTN = stationTN;
	}

	public FloatWritable getValueTN() {
		return valueTN;
	}

	public void setValueTN(FloatWritable valueTN) {
		this.valueTN = valueTN;
	}

	public Text getStationGR() {
		return stationGR;
	}

	public void setStationGR(Text stationGR) {
		this.stationGR = stationGR;
	}

	public FloatWritable getValueGR() {
		return valueGR;
	}

	public void setValueGR(FloatWritable valueGR) {
		this.valueGR = valueGR;
	}
}