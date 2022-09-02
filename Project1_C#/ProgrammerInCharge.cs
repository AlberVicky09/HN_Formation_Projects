namespace Project1_C_
{
    public class ProgrammerInCharge : IEmployee
    {
        //Constructors
        public ProgrammerInCharge() : base(){}

        public ProgrammerInCharge(string fName, string lName, int d, string a) : base(fName, lName, d, a){}

        public ProgrammerInCharge(string fName, string lName, int dWorked, int dCharge, string act, DateTime sDate) : base(fName, lName, dWorked, dCharge, act, sDate){}
    }
}