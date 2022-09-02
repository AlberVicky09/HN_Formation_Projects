using System.Text;

namespace Project1_C_
{
    static class ITCompany
    {
        static void Main(string[] args)
        {
            //Try to get the txt with the info
            HashSet<IEmployee> programmerList = new HashSet<IEmployee>();
            HashSet<ProjectTeam> projectList = new HashSet<ProjectTeam>();
            string dir = Environment.CurrentDirectory;
            string path = dir + @"\employees.txt";

            //Check if file exists
            if(File.Exists(path)){
                //Store all data in a txt
                FileStream file = new FileStream(path, FileMode.Open, FileAccess.Read);
                string fileTxt = "";
                byte[] buf = new byte[1024];
                int c;
                while((c = file.Read(buf, 0, buf.Length)) > 0){
                    fileTxt += Encoding.UTF8.GetString(buf, 0, c);
                }
                file.Close();

                //Split string in teams
                string[] teams = fileTxt.Split("}");
                string[] teamInfo;
                string[] personInfo;
                for(int i = 0; i < teams.Length-1; i++){
                    teams[i] = teams[i].Remove(0,1);
                    //Split team in members
                    teamInfo = teams[i].Split(";");
        
                    //Retrieve team leader info
                    personInfo = teamInfo[1].Split(",");
                    ProgrammerInCharge charge = new ProgrammerInCharge(personInfo[0], personInfo[1], int.Parse(personInfo[2]), int.Parse(personInfo[3]), personInfo[4], DateTime.Parse(personInfo[5]));
                    programmerList.Add(charge);
                    
                    //Retrieve team info
                    personInfo = teamInfo[0].Split(",");
                    ProjectTeam team = new ProjectTeam(personInfo[0], charge, Boolean.Parse(personInfo[1]), int.Parse(personInfo[2]), int.Parse(personInfo[3]), DateTime.Parse(personInfo[4]));
                    projectList.Add(team);

                    //Retrieve team crew info
                    for(int j = 2; j < teamInfo.Length -1; j++){
                        personInfo = teamInfo[j].Split(",");
                        ProgrammerJunior junior = new ProgrammerJunior(personInfo[0], personInfo[1], int.Parse(personInfo[2]), int.Parse(personInfo[3]), personInfo[4], DateTime.Parse(personInfo[5]));
                        programmerList.Add(junior);
                        team.AddCrewMember(junior);
                    }
                }
            }else{
                Console.WriteLine("File not existing, creating new teams...");
                //Create the teams if they dont exist
                ProgrammerInCharge programmer1 = new ProgrammerInCharge("A", "A", 0, 5, "UI", DateTime.Now);
                programmerList.Add(programmer1);
                ProgrammerJunior programmer2 = new ProgrammerJunior("B", "B", 0, 3, "UX", DateTime.Now);
                programmerList.Add(programmer2);
                ProgrammerJunior programmer3 = new ProgrammerJunior("C", "C", 0, 3, "UI", DateTime.Now);
                programmerList.Add(programmer3);
                ProgrammerJunior programmer4 = new ProgrammerJunior("D", "D", 0, 7, "Design", DateTime.Now);
                programmerList.Add(programmer4);

                List<ProgrammerJunior> listProgrammer1 = new List<ProgrammerJunior>();
                listProgrammer1.Add(programmer2);
                List<ProgrammerJunior> listProgrammer2 = new List<ProgrammerJunior>();
                listProgrammer2.Add(programmer3);
                listProgrammer2.Add(programmer4);

                ProjectTeam team1 = new ProjectTeam("team1", programmer1, listProgrammer1, false, 15, 0, DateTime.Now);
                projectList.Add(team1);
                ProjectTeam team2 = new ProjectTeam("team2", programmer1, listProgrammer2, true, 20, 0, DateTime.Now);
                projectList.Add(team2);
            }
                     
            //Update the teams
            foreach(ProjectTeam project in projectList)
                project.UpdateWorkDays();

            //Store all info in a string
            string text = "";
            foreach(ProjectTeam project in projectList){
                //Project details
                text += "{" + project.teamName + "," + project.fullPayment.ToString() + "," + project.totalDays + "," + project.workedDays + "," + project.startingDate.ToString() + ";";
                //Team leader
                text += project.teamLeader.firstName + "," + project.teamLeader.lastName + "," + project.teamLeader.daysWorked + "," + project.teamLeader.daysInCharge + "," + project.teamLeader.activity + "," + project.teamLeader.startingDate.ToString() + ";";
                //Team crew
                foreach(ProgrammerJunior programmer in project.teamCrew){
                    text += programmer.firstName + "," + programmer.lastName + "," + programmer.daysWorked + "," + programmer.daysInCharge + "," + programmer.activity + "," + programmer.startingDate.ToString() + ";";
                }
                text += "}";
            }

            //Store string in a txt
            FileStream fs = new FileStream(path, FileMode.Create, FileAccess.Write);
            byte[] byteText = Encoding.Default.GetBytes(text);
            fs.Write(byteText);
            fs.Close();

            //Show in screen
            Console.WriteLine("IT COMPANY - Report");
            Console.WriteLine("IT Company is currently composed of {0} project teams, and {1} programmers", projectList.Count, programmerList.Count);

            int daysRemaining = 0;
            int daysWorked = 0;
            foreach(ProjectTeam p in projectList){
                daysRemaining += p.totalDays - p.workedDays;
                daysWorked += p.workedDays;
            }
            Console.WriteLine("This month, {0} days have been consummed by {1} programmers, and {2} days still in charge", daysWorked, programmerList.Count, daysRemaining);
            
            Console.WriteLine("Project teams details");
            foreach(ProjectTeam project in projectList){
                Console.WriteLine("Project team : {0}", project.teamName);
                if(project.fullPayment){
                    Console.WriteLine("{0}, {1}, in  charge of {2} employees from {3} to {4} (duration {5}), this month : {6} days (total cost = {7} $)", project.teamLeader.lastName, project.teamLeader.firstName, project.teamCrew.Count, project.teamLeader.startingDate.Date.ToString("d"), project.teamLeader.startingDate.AddDays(project.teamLeader.daysInCharge).Date.ToString("d"), project.teamLeader.daysInCharge, project.workedDays, project.workedDays*12);
                    foreach(ProgrammerJunior prog in project.teamCrew)
                        Console.WriteLine("{0}, {1}, working in {2} from {3} to {4} (duration {5}), this month : {6} days (total cost = {7} $)", prog.lastName, prog.firstName, project.teamName, prog.startingDate.Date.ToString("d"), prog.startingDate.AddDays(prog.daysInCharge).Date.ToString("d"), prog.daysInCharge, prog.daysWorked, prog.daysWorked*12);
                }else{
                    Console.WriteLine("{0}, {1}, in  charge of {2} employees from {3} to {4} (duration {5}), this month : {6} days (total cost = {7} $)", project.teamLeader.lastName, project.teamLeader.firstName, project.teamCrew.Count, project.teamLeader.startingDate.Date.ToString("d"), project.teamLeader.startingDate.AddDays(project.teamLeader.daysInCharge).Date.ToString("d"), project.teamLeader.daysInCharge, project.workedDays, project.workedDays*6);
                    foreach(ProgrammerJunior prog in project.teamCrew)
                        Console.WriteLine("{0}, {1}, working in {2} from {3} to {4} (duration {5}), this month : {6} days (total cost = {7} $)", prog.lastName, prog.firstName, project.teamName, prog.startingDate.Date.ToString("d"), prog.startingDate.AddDays(prog.daysInCharge).Date.ToString("d"), prog.daysInCharge, prog.daysWorked, prog.daysWorked*6);
                }
            }
        }
    }
}