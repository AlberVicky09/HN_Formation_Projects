namespace Project1_C_
{
    public abstract class IEmployee
    {
        //Attributes
        public string firstName {get; private set;}
        public string lastName {get; private set;}
        public int daysWorked {get; private set;}
        public int daysInCharge {get; private set;}
        public string activity {get; private set;}
        public DateTime startingDate {get; private set;}

        //Constructors
        protected IEmployee(){
            firstName = "";
            lastName = "";
            daysWorked = 0;
            daysInCharge = 0;
            activity = "";
            startingDate = DateTime.Now;
        }

        protected IEmployee(string fName, string lName, int d, string act, DateTime sDate){
            firstName = fName;
            lastName = lName;
            daysWorked = 0;
            daysInCharge = d;
            activity = act;
            startingDate = sDate;
        }

        protected IEmployee(string fName, string lName, int dWorked, int dCharge, string act, DateTime sDate){
            firstName = fName;
            lastName = lName;
            daysWorked = dWorked;
            daysInCharge = dCharge;
            activity = act;
            startingDate = sDate;
        }
        
        //Methods
        public bool UpdateWorkDays(){
            daysWorked++;
            return daysWorked == daysInCharge;
        }

        public void SetStartingDate(){ startingDate = DateTime.Now; }

        public void SetActivity(string act){ activity = act; }
    }
}