//*** Merlin

//var MerlinID;
//var MerlinACS;
//hkdownload.Connected = true;
//MerlinLoaded = LoadLocalAgent(MerlinID, MerlinACS);

var charID1="";
var charID2="";
var Merlin, Peedy;
var showReq;

function loadAgent() {
	if (charID1 != "")	MSAgent.Characters.UnLoad(charID1);
	if (charID2 != "")	MSAgent.Characters.UnLoad(charID2);

	charID1 = "MyMerlin";
	charID2 = "MyPeedy";
	MSAgent.Connected = true;
	MSAgent.Characters.Load(charID1, "http://agent.microsoft.com/agent2/chars/merlin/merlin.acf");
	MSAgent.Characters.Load(charID2, "http://agent.microsoft.com/agent2/chars/peedy/peedy.acf");
	Merlin = MSAgent.Characters.Character(charID1);
	Peedy = MSAgent.Characters.Character(charID2);

	Peedy.get("state","hiding");
	Peedy.get("state","showing");
	Peedy.moveto(530,300,0);

	Merlin.get("state","hiding");
	Merlin.get("state","showing");
	Merlin.moveto(400,300,0);

	Merlin.get("animation","Greet");
	Merlin.get("animation","Announce");
	Merlin.get("animation","Alert");
	Merlin.get("animation","Pleased");
	Merlin.get("animation","Read");
	Merlin.get("animation","ReadContinued");
	Merlin.get("animation","ReadReturn");Merlin.Play("Announce");
	Merlin.get("animation","GestureUp");
	Merlin.get("animation","Suggest");
	Merlin.get("animation","GestureLeft");
	Merlin.get("animation","GestureDown");
	Merlin.get("animation","Congratulate_2");
	Merlin.get("animation","DoMagic1");
	Merlin.get("animation","DoMagic2");
	Merlin.get("animation","Process");
	Merlin.get("animation","Explain");
	Merlin.get("animation","Wave");
	Merlin.get("animation","LookLeftBlink");
	Merlin.get("animation","LookLeftReturn");
	Merlin.get("animation","Idle1_2");
	Merlin.get("animation","Idle2_2");

	Peedy.get("animation","GetAttention");
	Peedy.get("animation","Greet");
	Peedy.get("animation","Wave");
	Peedy.get("animation","Announce");
	Peedy.get("animation","Explain");
	Peedy.get("animation","Pleased");

	Merlin.Show();

	Merlin.Play("Announce");
	Merlin.Play("Greet");

	Merlin.Play("Pleased");
	Merlin.Speak("Welcome, everyone!");
	Merlin.Speak("I am Merlin,");
	Merlin.Speak("the wizard !");
	Merlin.Play("DoMagic1");
	Merlin.Play("DoMagic2");

	Merlin.Play("Pleased");
	Merlin.Speak("Actrellis is a wonderful product leaded by Al Ellmen, Brian McGuinty,");
	Merlin.Speak("Ken Clark and Steven Yang,");
	Merlin.Speak("and members are");

	Merlin.Play("Read");
	Merlin.Speak("Lei Liu, Brandon Wu, Xiang Chen, Celina Yang");
	Merlin.Speak("Ava Liu, Yao Cheng, Hui Zhang, Sejin Kim, Ting Ouyang");
	Merlin.Speak("and a good old friend of mine... uh...");

	Merlin.Play("ReadContinued");
	Merlin.Speak("Jason Wang.");
	Merlin.Play("ReadReturn");

	Merlin.Play("Explain");
	Merlin.Speak("We also have great Q.A. people,");
	Merlin.Speak("like Cesar Gaudin, Sean Palmer and Steven Culbertson, just to name a few.");
	Merlin.Speak("Let's give them applause !");
	Merlin.Play("Congratulate_2");

	Merlin.Play("GestureUp");
	Merlin.Speak("There must be a lot of great stuff in here");

	Merlin.Play("Suggest");
	Merlin.Speak("I think you will like to try it out");

	Merlin.Play("GestureDown");
	Merlin.Speak("Enjoy yourself !");

	Merlin.Play("Alert");
	Merlin.Speak("Wait, did you hear something ?");

	Merlin.Play("LookLeftBlink");
	Merlin.Play("LookLeftReturn");

	showReq = Merlin.Play("GestureLeft");
	Merlin.Speak("Here comes my old friend, Peedy !");

	Peedy.Wait(showReq);
	Peedy.Show();
	Peedy.Play("GetAttention");

	showReq = Merlin.Speak("Say hello to everyone !");
	Merlin.Play("Idle1_2");
	Merlin.Play("Idle2_2");

	Peedy.Wait(showReq);
	Peedy.Play("Greet");
	Peedy.Play("Announce");
	Peedy.Play("Explain");
	Peedy.Speak("Hello everyone !");
	showReq = Peedy.Play("Pleased");

	Merlin.Wait(showReq);
	Merlin.Play("Pleased");
	Merlin.Speak("All right,");
	Merlin.Speak("we'll see you soon !");
	showReq = Merlin.Speak("Adios, amigo. ");

	Merlin.Play("Wave");

	Peedy.Wait(showReq);
	Peedy.Play("Wave");

	Merlin.Hide();
	Peedy.Hide();
}

