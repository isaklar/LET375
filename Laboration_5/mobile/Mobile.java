import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Mobile {

	private enum MobileType { SIMPLE, COMPOSITE }
	private MobileType type;
	private float weight;                   // Simple
	private float leftLength, rightLength;  // Composite
	private Mobile left, right;

	public Mobile( float weight ) {
		type = MobileType.SIMPLE;
		this.weight = weight;
		left = null;
		right = null;

	}

	public Mobile( Mobile left, float leftLength, Mobile right, float rightLength ) {
		type = MobileType.COMPOSITE;
		this.left = left;
		this.right = right;
	  this.leftLength = leftLength;
	  this.rightLength = rightLength;
	}

	// Return the total mass of the mobile
	public float getWeight() {
		if ( isSimple() )
			return weight;
		else
			return left.getWeight() + right.getWeight();
	}

	// Return the maximum height of the mobile
	public int getHeight() {
	    if(isSimple()){ //basfallet
					return 1;
			}else{
					int hr = right.getHeight();
					int hl = left.getHeight();
					return Math.max(hr, hl) + 1;
			}
	}

	// Print the leaves of the mobile
	// Kör till
	public void flatten()  {
	      if(isSimple()){ // basfallet
					System.out.print(getWeight()+", ");
				}else{
				left.flatten();
				right.flatten();
			}
	}

//	Print a structured view of the mobile
public void prettyPrint() {
	if(isSimple()){ //basfallet
				System.out.print("("+getWeight()+")");
	}else{
			System.out.print("[");
			left.prettyPrint();
			System.out.print(",");
			System.out.print(leftLength);
			System.out.print(",");
			right.prettyPrint();
			System.out.print(",");
			System.out.print(rightLength);
			System.out.print("]");
		}
}

// Determine if the mobile is balanced
	public boolean isBalanced() {
		final double eps = 0.000001;
		return isSimple() ||
		    left.isBalanced() && right.isBalanced() &&
		        Math.abs( leftLength * left.getWeight() -
				rightLength * right.getWeight() ) < eps;
	}

// Determine if two mobiles are equal
	public boolean equals(  Mobile rhs ) {
			final double eps = 0.000001; // vet inte riktigt vad det är, kommer från metoden balanced, vilket uno skrivit.
			Mobile m = rhs;

			if(!(rhs instanceof Mobile)) // Tittar om rhs är av objektet Mobile
			return false;

			if(isSimple() != m.isSimple()) // tittar om de har samma basfall
	    return false;

			if(isSimple()){ // om basfall, så returnera absolutbeloppet av bådas "vikt" och längd.
				return Math.abs(getWeight() - m.getWeight()) < eps;
			}else{
				return Math.abs(leftLength - m.leftLength) < eps && Math.abs(rightLength - m.rightLength) < eps;
			}
	}

//	Return a clone of this mobile
	public Mobile clone() {
         if(isSimple()) // basfall
				 return new Mobile(weight); // om ja, skapa new mobile
				 else{
					 return new Mobile(left.clone(), leftLength, right.clone(), rightLength); // skapa ett ny mobile, unik
				 }
	}

// Change this mobile to its mirror image
	public void mirror() {
         if(isSimple()){// basfallet
					 return;
				 }else{// Kör en swap för att vända på skiten.
					 Mobile tempMobile = right;
					 float tempLength = rightLength;
					 right = left;
					 rightLength = leftLength;
					 left = tempMobile;
					 leftLength = tempLength;
				 }
	}

	private boolean isSimple() {
		return type == MobileType.SIMPLE;
	}

	public static void main(String[] args) {
		Mobile  m1 = new Mobile( 1 ),
		        m2 = new Mobile( new Mobile( 2 ), 6,  new Mobile( 3 ), 4 ),
		        m = new Mobile( m1, 10, m2, 2 );

		System.out.println("Total mass: " + m.getWeight() );

		System.out.println("Height:     " + m.getHeight() );
		m.flatten(); System.out.println();
		m.prettyPrint(); System.out.println();
		if ( m.isBalanced() )
			System.out.println("Balanced!, isBalanced works!");
		else
			System.out.println("Not balanced! isBalanced doesnt work..");

		Mobile m22 = new Mobile( new Mobile( 2 ), 6,  new Mobile( 3 ), 4 ),
		       m3 = new Mobile( m1, 10, m22, 2 );
		if ( m.equals(m3) )
			System.out.println("Equal! equals works!");		// They should be!
		else
			System.out.println("Not equal! equals doenst work duuuh....");

		Mobile c = m.clone();
		if ( c.equals(m) )
			System.out.println("Equal! clone works!");		// They should be!
		else
			System.out.println("Not equal! clone doesnt work!");

		if ( c == m )
			System.out.println("Identical! shit....");	// They should definately not!
		else
			System.out.println("Not identical! yay!");

		m.mirror();
		m.prettyPrint(); System.out.println();
		m.mirror();
		m.prettyPrint(); System.out.println();

	}
}
