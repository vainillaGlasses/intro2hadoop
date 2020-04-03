package hadoop;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.WritableComparable;


public class TopTenCountry implements WritableComparable {
     
      private Text countryOne;
      private FloatWritable valueOne;
      
      private Text countryTwo;
      private FloatWritable valueTwo;
      
      private Text countryThree;
      private FloatWritable valueThree;
      
      private Text countryFour;
      private FloatWritable valueFour;
      
      private Text countryFive;
      private FloatWritable valueFive;
      
      private Text countrySix;
      private FloatWritable valueSix;
      
      private Text countrySeven;
      private FloatWritable valueSeven;
      
      private Text countryEight;
      private FloatWritable valueEight;
      
      private Text countryNine;
      private FloatWritable valueNine;
      
      private Text countryTen;
      private FloatWritable valueTen;
      
      
      public TopTenCountry() {
    	  super();
      }
      
      public TopTenCountry(Text countryOne, FloatWritable valueOne, Text countryTwo, FloatWritable valueTwo,
			Text countryThree, FloatWritable valueThree, Text countryFour, FloatWritable valueFour, Text countryFive,
			FloatWritable valueFive, Text countrySix, FloatWritable valueSix, Text countrySeven,
			FloatWritable valueSeven, Text countryEight, FloatWritable valueEight, Text countryNine,
			FloatWritable valueNine, Text countryTen, FloatWritable valueTen) {
		super();
		this.countryOne = countryOne;
		this.valueOne = valueOne;
		this.countryTwo = countryTwo;
		this.valueTwo = valueTwo;
		this.countryThree = countryThree;
		this.valueThree = valueThree;
		this.countryFour = countryFour;
		this.valueFour = valueFour;
		this.countryFive = countryFive;
		this.valueFive = valueFive;
		this.countrySix = countrySix;
		this.valueSix = valueSix;
		this.countrySeven = countrySeven;
		this.valueSeven = valueSeven;
		this.countryEight = countryEight;
		this.valueEight = valueEight;
		this.countryNine = countryNine;
		this.valueNine = valueNine;
		this.countryTen = countryTen;
		this.valueTen = valueTen;
	}

	@Override
      public void readFields(DataInput in) throws IOException {
			this.countryOne.readFields(in);
			this.valueOne.readFields(in);
			this.countryTwo.readFields(in);
			this.valueTwo.readFields(in);
			this.countryThree.readFields(in);
			this.valueThree.readFields(in);
			this.countryFour.readFields(in);
			this.valueFour.readFields(in);
			this.countryFive.readFields(in);
			this.valueFive.readFields(in);
			this.countrySix.readFields(in);
			this.valueSix.readFields(in);
			this.countrySeven.readFields(in);
			this.valueSeven.readFields(in);
			this.countryEight.readFields(in);
			this.valueEight.readFields(in);
			this.countryNine.readFields(in);
			this.valueNine.readFields(in);
			this.countryTen.readFields(in);
			this.valueTen.readFields(in);
      }

      @Override
      public void write(DataOutput out) throws IOException {
	    	  this.countryOne.write(out);
	    	  this.valueOne.write(out);
	    	  this.countryTwo.write(out);
	    	  this.valueTwo.write(out);
	    	  this.countryThree.write(out);
	    	  this.valueThree.write(out);
	    	  this.countryFour.write(out);
	    	  this.valueFour.write(out);
	    	  this.countryFive.write(out);
	    	  this.valueFive.write(out);
	    	  this.countrySix.write(out);
	    	  this.valueSix.write(out);
	    	  this.countrySeven.write(out);
	    	  this.valueSeven.write(out);
	    	  this.countryEight.write(out);
	    	  this.valueEight.write(out);
	    	  this.countryNine.write(out);
	    	  this.valueNine.write(out);
	    	  this.countryTen.write(out);
	    	  this.valueTen.write(out);
      }

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	@Override
	public String toString() {
		return countryOne + ";" + valueOne + "\t" 
			 + countryTwo + ";" + valueTwo + "\t" 
			 + countryThree + ";" + valueThree + "\t" 
			 + countryFour + ";" + valueFour + "\t" 
			 + countryFive + ";" + valueFive + "\t" 
			 + countrySix + ";" + valueSix + "\t"
			 + countrySeven + ";" + valueSeven + "\t"
			 + countryEight + ";" + valueEight + "\t" 
			 + countryNine + ";" + valueNine + "\t" 
			 + countryTen + ";" + valueTen; 
	}

	public Text getCountryOne() {
		return countryOne;
	}

	public void setCountryOne(Text countryOne) {
		this.countryOne = countryOne;
	}

	public FloatWritable getValueOne() {
		return valueOne;
	}

	public void setValueOne(FloatWritable valueOne) {
		this.valueOne = valueOne;
	}

	public Text getCountryTwo() {
		return countryTwo;
	}

	public void setCountryTwo(Text countryTwo) {
		this.countryTwo = countryTwo;
	}

	public FloatWritable getValueTwo() {
		return valueTwo;
	}

	public void setValueTwo(FloatWritable valueTwo) {
		this.valueTwo = valueTwo;
	}

	public Text getCountryThree() {
		return countryThree;
	}

	public void setCountryThree(Text countryThree) {
		this.countryThree = countryThree;
	}

	public FloatWritable getValueThree() {
		return valueThree;
	}

	public void setValueThree(FloatWritable valueThree) {
		this.valueThree = valueThree;
	}

	public Text getCountryFour() {
		return countryFour;
	}

	public void setCountryFour(Text countryFour) {
		this.countryFour = countryFour;
	}

	public FloatWritable getValueFour() {
		return valueFour;
	}

	public void setValueFour(FloatWritable valueFour) {
		this.valueFour = valueFour;
	}

	public Text getCountryFive() {
		return countryFive;
	}

	public void setCountryFive(Text countryFive) {
		this.countryFive = countryFive;
	}

	public FloatWritable getValueFive() {
		return valueFive;
	}

	public void setValueFive(FloatWritable valueFive) {
		this.valueFive = valueFive;
	}

	public Text getCountrySix() {
		return countrySix;
	}

	public void setCountrySix(Text countrySix) {
		this.countrySix = countrySix;
	}

	public FloatWritable getValueSix() {
		return valueSix;
	}

	public void setValueSix(FloatWritable valueSix) {
		this.valueSix = valueSix;
	}

	public Text getCountrySeven() {
		return countrySeven;
	}

	public void setCountrySeven(Text countrySeven) {
		this.countrySeven = countrySeven;
	}

	public FloatWritable getValueSeven() {
		return valueSeven;
	}

	public void setValueSeven(FloatWritable valueSeven) {
		this.valueSeven = valueSeven;
	}

	public Text getCountryEight() {
		return countryEight;
	}

	public void setCountryEight(Text countryEight) {
		this.countryEight = countryEight;
	}

	public FloatWritable getValueEight() {
		return valueEight;
	}

	public void setValueEight(FloatWritable valueEight) {
		this.valueEight = valueEight;
	}

	public Text getCountryNine() {
		return countryNine;
	}

	public void setCountryNine(Text countryNine) {
		this.countryNine = countryNine;
	}

	public FloatWritable getValueNine() {
		return valueNine;
	}

	public void setValueNine(FloatWritable valueNine) {
		this.valueNine = valueNine;
	}

	public Text getCountryTen() {
		return countryTen;
	}

	public void setCountryTen(Text countryTen) {
		this.countryTen = countryTen;
	}

	public FloatWritable getValueTen() {
		return valueTen;
	}

	public void setValueTen(FloatWritable valueTen) {
		this.valueTen = valueTen;
	}

	
	
}