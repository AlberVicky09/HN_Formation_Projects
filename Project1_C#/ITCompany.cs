using System.Text;

namespace Project1_C_
{
    static class ITCompany
    {
        static void Main(string[] args)
        {
            #region Variables
            HashSet<ProgrammerInCharge> programmerInChargeList = new HashSet<ProgrammerInCharge>();
            HashSet<ProgrammerJunior> programmerJuniorList = new HashSet<ProgrammerJunior>();
            HashSet<ProjectTeam> projectList = new HashSet<ProjectTeam>();
            List<ProjectTeam> addProjectList = new List<ProjectTeam>();
            List<ProjectTeam> removeProjectList = new List<ProjectTeam>();
            string dir = Environment.CurrentDirectory;
            string path = dir + @"\employees.txt";
            string[] activities = {"UI", "UX", "Frontend", "Backend", "Database"};
            bool endCharge = false;
            int numJunnior = 0;
            DateTime systemDate;
            Random random = new Random();
            #endregion

            #region Get txt with info
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
                //Get system information
                systemDate = DateTime.Parse(teams[0]);
                //Get teams information
                string[] teamInfo;
                string[] personInfo;
                for(int i = 1; i < teams.Length-1; i++){
                    //Split team in members
                    teamInfo = teams[i].Split(";");
        
                    //Retrieve team leader info
                    personInfo = teamInfo[1].Split(",");
                    ProgrammerInCharge charge = new ProgrammerInCharge(personInfo[0], personInfo[1], int.Parse(personInfo[2]), int.Parse(personInfo[3]), personInfo[4], DateTime.Parse(personInfo[5]));
                    programmerInChargeList.Add(charge);
                    
                    //Retrieve team info
                    personInfo = teamInfo[0].Split(",");
                    ProjectTeam team = new ProjectTeam(personInfo[0], charge, Boolean.Parse(personInfo[1]), int.Parse(personInfo[2]), int.Parse(personInfo[3]), DateTime.Parse(personInfo[4]));
                    projectList.Add(team);

                    //Retrieve team crew info
                    for(int j = 2; j < teamInfo.Length -1; j++){
                        personInfo = teamInfo[j].Split(",");
                        ProgrammerJunior junior = new ProgrammerJunior(personInfo[0], personInfo[1], int.Parse(personInfo[2]), int.Parse(personInfo[3]), personInfo[4], DateTime.Parse(personInfo[5]));
                        programmerJuniorList.Add(junior);
                        team.AddCrewMember(junior);
                    }
                }
            }else{
                Console.WriteLine("File not existing, creating new teams...");
                //Create the teams if they dont exist
                systemDate = DateTime.Now;
                ProgrammerInCharge programmer1 = new ProgrammerInCharge(GenerateName(5), GenerateName(5), random.Next(3, 15), activities[random.Next(activities.Length)], systemDate);
                programmerInChargeList.Add(programmer1);
                ProgrammerJunior programmer2 = new ProgrammerJunior(GenerateName(5), GenerateName(5), random.Next(3, 15), activities[random.Next(activities.Length)], systemDate);
                programmerJuniorList.Add(programmer2);
                ProgrammerInCharge programmer3 = new ProgrammerInCharge(GenerateName(5), GenerateName(5), random.Next(3, 15), activities[random.Next(activities.Length)], systemDate);
                programmerInChargeList.Add(programmer3);
                ProgrammerJunior programmer4 = new ProgrammerJunior(GenerateName(5), GenerateName(5), random.Next(3, 15), activities[random.Next(activities.Length)], systemDate);
                programmerJuniorList.Add(programmer4);
                ProgrammerJunior programmer5 = new ProgrammerJunior(GenerateName(5), GenerateName(5), random.Next(3, 15), activities[random.Next(activities.Length)], systemDate);
                programmerJuniorList.Add(programmer5);

                List<ProgrammerJunior> listProgrammer1 = new List<ProgrammerJunior>();
                listProgrammer1.Add(programmer2);
                List<ProgrammerJunior> listProgrammer2 = new List<ProgrammerJunior>();
                listProgrammer2.Add(programmer4);
                listProgrammer2.Add(programmer5);

                ProjectTeam team1 = new ProjectTeam("team " + GenerateName(7), programmer1, listProgrammer1, false, 15, 0, systemDate);
                projectList.Add(team1);
                ProjectTeam team2 = new ProjectTeam("team " + GenerateName(7), programmer3, listProgrammer2, true, 20, 0, systemDate);
                projectList.Add(team2);
            }
            #endregion Get txt with info  
            
            #region Update the teams
            systemDate = systemDate.AddDays(1);
            Console.WriteLine("________________________________________________________________________");
            foreach(ProjectTeam project in projectList){
                //Check if project has ended
                endCharge = false;
                numJunnior = 0;
                if(project.UpdateWorkDays(ref endCharge, ref numJunnior)){
                    Console.WriteLine("Project " + project.teamName + " ended.\nTotal days worked: " + project.workedDays + ". Good work!\n");
                    //Destroy ended project
                    programmerInChargeList.Remove(project.teamLeader);
                    foreach(ProgrammerJunior junior in project.teamCrew)
                        programmerJuniorList.Remove(junior);
                    removeProjectList.Add(project);

                    //Create new programmer in charge
                    ProgrammerInCharge newCharge = new ProgrammerInCharge(GenerateName(5), GenerateName(5), random.Next(3, 15), activities[random.Next(activities.Length)], systemDate);
                    programmerInChargeList.Add(newCharge);
                    //Create new programmer list
                    int r = random.Next(1, 4);
                    List<ProgrammerJunior> newListProgrammer = new List<ProgrammerJunior>();
                    for(int i = 0; i < r; i++){
                        ProgrammerJunior newJunior = new ProgrammerJunior(GenerateName(5), GenerateName(5), random.Next(3, 15), activities[random.Next(activities.Length)], systemDate);
                        programmerJuniorList.Add(newJunior);
                        newListProgrammer.Add(newJunior);
                    }
                    //Create new project
                    int prob = random.Next(100);
                    ProjectTeam newProject = new ProjectTeam("team " + GenerateName(7), newCharge, newListProgrammer, prob <= 50, random.Next(5,22), 0, systemDate);
                    addProjectList.Add(newProject);
                    Console.Write("New project " + newProject.teamName + " created with " + newCharge.firstName + " in charge and " + r + " junnior programmers (");
                    for(int i = 0; i < newListProgrammer.Count; i++){
                        if(i != 0)
                            Console.Write(", ");
                        Console.Write(newListProgrammer[i].firstName);
                    }

                    if(newProject.fullPayment)
                        Console.WriteLine(")\nWith work lenght of " + newProject.totalDays + " and a payment of 12$ per hour\n");
                    else
                        Console.WriteLine(")\nWith work lenght of " + newProject.totalDays + " and a payment of 6$ per hour\n");
                }else{
                    //Create new programmer in charge if it has ended its work
                    if(endCharge){
                        ProgrammerInCharge programmer = new ProgrammerInCharge(GenerateName(5), GenerateName(5), random.Next(3, 15), activities[random.Next(activities.Length)], systemDate);
                        project.SetTeamLeader(programmer);
                        programmerInChargeList.Add(programmer);
                        Console.WriteLine(project.teamName + ": New leader " + programmer.firstName + " in charge of team for " + programmer.daysInCharge + " days. Welcome!");
                    }
                    //Create new programmers (one for each programmer ended)
                    for(int i = 0; i < numJunnior; i++){
                        ProgrammerJunior programmer = new ProgrammerJunior(GenerateName(5), GenerateName(5), random.Next(3, 15), activities[random.Next(activities.Length)], systemDate);
                        project.AddCrewMember(programmer);
                        programmerJuniorList.Add(programmer);
                        Console.WriteLine(project.teamName + ": New worker " + programmer.firstName + " working in " + programmer.activity + " for " + programmer.daysInCharge + " days. Welcome!");
                    }
                    if(endCharge || numJunnior > 0)
                        Console.WriteLine();
                }
            }
            //Remove ended projects
            foreach(ProjectTeam t in removeProjectList)
                projectList.Remove(t);
            //Add new projects
            foreach(ProjectTeam t in addProjectList)
                projectList.Add(t);
            #endregion Update the teams

            #region ExportToTxt
            //Store all info in a string
            string text = systemDate.ToString() + "}";
            foreach(ProjectTeam project in projectList){
                //Project details
                text += project.teamName + "," + project.fullPayment.ToString() + "," + project.totalDays + "," + project.workedDays + "," + project.startingDate.ToString() + ";";
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
            #endregion ExportToTxt

            #region Show in screen
            Console.WriteLine("________________________________________________________________________\nIT COMPANY - Report (Day: " + systemDate.ToString("d") + ")");
            Console.WriteLine("IT Company is currently composed of {0} project teams, {1} programmers in charge and {2} programmers junnior", projectList.Count, programmerInChargeList.Count, programmerJuniorList.Count);

            int daysRemaining = 0;
            int daysWorked = 0;
            foreach(ProjectTeam p in projectList){
                daysRemaining += p.totalDays - p.workedDays;
                daysWorked += p.workedDays;
            }
            Console.WriteLine("This month, {0} days have been consummed by {1} programmers, and {2} days still in charge", daysWorked, programmerInChargeList.Count + programmerJuniorList.Count, daysRemaining);
            
            Console.WriteLine("Project teams details");
            foreach(ProjectTeam project in projectList){
                Console.WriteLine("\nProject team : {0}. Days worked : {1}. Total days : {2}", project.teamName, project.workedDays, project.totalDays);
                if(project.fullPayment){
                    Console.WriteLine("{0}, {1}, in  charge of {2} employees from {3} to {4} (duration {5} days), this month : {6} days (total cost = {7} $)", project.teamLeader.lastName, project.teamLeader.firstName, project.teamCrew.Count, project.teamLeader.startingDate.Date.ToString("d"), project.teamLeader.startingDate.AddDays(project.teamLeader.daysInCharge).Date.ToString("d"), project.teamLeader.daysInCharge, project.teamLeader.daysWorked, project.teamLeader.daysWorked*12);
                    foreach(ProgrammerJunior prog in project.teamCrew)
                        Console.WriteLine("{0}, {1}, working in {2} from {3} to {4} (duration {5}), this month : {6} days (total cost = {7} $)", prog.lastName, prog.firstName, project.teamName, prog.startingDate.Date.ToString("d"), prog.startingDate.AddDays(prog.daysInCharge).Date.ToString("d"), prog.daysInCharge, prog.daysWorked, prog.daysWorked*12);
                }else{
                    Console.WriteLine("{0}, {1}, in  charge of {2} employees from {3} to {4} (duration {5}), this month : {6} days (total cost = {7} $)", project.teamLeader.lastName, project.teamLeader.firstName, project.teamCrew.Count, project.teamLeader.startingDate.Date.ToString("d"), project.teamLeader.startingDate.AddDays(project.teamLeader.daysInCharge).Date.ToString("d"), project.teamLeader.daysInCharge, project.teamLeader.daysWorked, project.teamLeader.daysWorked*6);
                    foreach(ProgrammerJunior prog in project.teamCrew)
                        Console.WriteLine("{0}, {1}, working in {2} from {3} to {4} (duration {5}), this month : {6} days (total cost = {7} $)", prog.lastName, prog.firstName, project.teamName, prog.startingDate.Date.ToString("d"), prog.startingDate.AddDays(prog.daysInCharge).Date.ToString("d"), prog.daysInCharge, prog.daysWorked, prog.daysWorked*6);
                }
            }
            Console.WriteLine();
            #endregion Show in screen
        }

        #region RandomNameGenerator
        public static string GenerateName(int len){ 
            Random r = new Random();
            string[] consonants = { "b", "c", "d", "f", "g", "h", "j", "k", "l", "m", "l", "n", "p", "q", "r", "s", "sh", "zh", "t", "v", "w", "x" };
            string[] vowels = { "a", "e", "i", "o", "u", "ae", "y" };
            string Name = "";
            Name += consonants[r.Next(consonants.Length)].ToUpper();
            Name += vowels[r.Next(vowels.Length)];
            int b = 2; //b tells how many times a new letter has been added. It's 2 right now because the first two letters are already in the name.
            while (b < len)
            {
                Name += consonants[r.Next(consonants.Length)];
                b++;
                Name += vowels[r.Next(vowels.Length)];
                b++;
            }

            return Name;
        }
        #endregion RandomNameGenerator
    }
}