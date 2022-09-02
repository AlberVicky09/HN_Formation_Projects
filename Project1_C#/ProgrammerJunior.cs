namespace Project1_C_
{
    public class ProgrammerJunior : IEmployee
    {
        //Constructors
        public ProgrammerJunior() : base(){}

        public ProgrammerJunior(string fName, string lName, int d, string a) : base(fName, lName, d, a){}

        public ProgrammerJunior(string fName, string lName, int dWorked, int dCharge, string act, DateTime sDate) : base(fName, lName, dWorked, dCharge, act, sDate){}
    }
}