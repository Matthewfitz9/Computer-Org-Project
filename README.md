## CS4421 Computer Organisation Project


-- A live system information monitor built in Java --


# Team Members


Joshua Corcoran  
Mathieu Gril  
Matthew Fitzgerald  
Artem Bosyi  


# Features (will update as time goes on)



# How to compile and run


Step One:

This project uses Maven to manage dependencies, so you must have Maven installed.

On Windows:  
    Open Powershell, type: winget install -e --id Apache.Maven

On Linux:  
    Open a terminal, type: sudo apt install maven

Verify installation:  
    Close and re-open terminal/Powershell, type: mvn -version


Step Two:

Clone/Pull from the github repo.  

Open the entire project folder in your IDE, not just one file.  

If all has gone correctly, your IDE will see the 'pom.xml' file and automatically start downloading OSHI using Maven. Wait for it to finish.  

Open src/main/java/com/JAMM/Main.java, and run it. It should display your system's basic hardware.  


# Team roles & tasks


Each person should create their own '.java' file for their module/modules. We will combine them into the Main.java file at the end.

Josh:  
    Project co-ordination, file integration, and Github management.
    Modules:  
        Memory
        Pci

Artem:  
    Tool visualisation.
    Module:  
        SystemInfo

Matthieu:  
    Documentation and code readability.
    Module:  
        CPU

Matthew:  
    Creativity/Innovation.
    Modules:  
        Disk
        Usb
