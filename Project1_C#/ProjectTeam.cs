namespace Project1_C_
{
    public class ProjectTeam
    {
        //Attributes
        public string teamName {get; private set;}
        public ProgrammerInCharge teamLeader {get; private set;}
        public List<ProgrammerJunior> teamCrew {get; private set;}
        public bool fullPayment {get; private set;}
        public int totalDays {get; private set;}
        public int workedDays {get; private set;}
        public DateTime startingDate {get; private set;}

        //Constructors
        public ProjectTeam(string name, ProgrammerInCharge leader, bool payment, int days){
            teamName = name;
            teamLeader = leader;
            teamCrew = new List<ProgrammerJunior>();
            fullPayment = payment;
            totalDays = days;
            workedDays = 0;
            startingDate = DateTime.Now;
        }

        public ProjectTeam(string name, ProgrammerInCharge leader, List<ProgrammerJunior> crew, bool payment, int days){
            teamName = name;
            teamLeader = leader;
            teamCrew = crew;
            fullPayment = payment;
            totalDays = days;
            workedDays = 0;
            startingDate = DateTime.Now;
        }
        
        public ProjectTeam(string name, ProgrammerInCharge leader, bool payment, int tDays, int wDays, DateTime sDate){
            teamName = name;
            teamLeader = leader;
            teamCrew = new List<ProgrammerJunior>();
            fullPayment = payment;
            totalDays = tDays;
            workedDays = wDays;
            startingDate = sDate;
        }

        public ProjectTeam(string name, ProgrammerInCharge leader, List<ProgrammerJunior> crew, bool payment, int tDays, int wDays, DateTime sDate){
            teamName = name;
            teamLeader = leader;
            teamCrew = crew;
            fullPayment = payment;
            totalDays = tDays;
            workedDays = wDays;
            startingDate = sDate;
        }

        //Methods
        public void SetTeamLeader(ProgrammerInCharge l){ teamLeader = l; }
        public void AddCrewMember(ProgrammerJunior p){ teamCrew.Add(p); }
        public bool RemoveCrewMember(ProgrammerJunior p){
            for(int i = 0; i < teamCrew.Count; i++){
                if(teamCrew[i].firstName.Equals(p.firstName) && teamCrew[i].lastName.Equals(p.lastName)){
                    teamCrew.RemoveAt(i);
                    return true;
                }
            }
            return false;
        }
        public bool UpdateWorkDays(ref bool chargeEnded, ref int junniorNum){

            //Update days and check if it has worked all of its days (for programmer in charge)
            if(teamLeader.UpdateWorkDays()){
                chargeEnded = true;
                Console.WriteLine(teamName + ": Leader (" + teamLeader.firstName + " " + teamLeader.lastName + ") has ended his job.\nTotal days worked: " + teamLeader.daysWorked + ". Good bye!");
            }

            //Update days and check if it has worked all of its days (for junior programmers)
            List<ProgrammerJunior> crewRemaining = new List<ProgrammerJunior>();
            foreach(ProgrammerJunior p in teamCrew){
                if(p.UpdateWorkDays()){
                    junniorNum++;
                    Console.WriteLine(teamName + ": Worker (" + p.firstName + " " + p.lastName + ") has ended his job,\nTotal days worked: " + p.daysWorked + ". Good bye!");
                }else{
                    crewRemaining.Add(p);
                }
            }
            teamCrew = crewRemaining;
            //Update work days and check if project is ended
            return ++workedDays == totalDays;
        }
    }
}