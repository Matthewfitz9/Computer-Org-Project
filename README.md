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

To compile from Command Line, open your terminal in the root folder (with the pom.xml file) and run this: mvn compile exec:java -Dexec.mainClass="com.JAMM.Main"

To open in IDE:
    
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

# Plagiarism Declaration – ISE 2024

We declare that this material, which we now submit for assessment, is entirely our own
work and has not been taken from the work of others, save and to the extent that such
work has been cited and acknowledged within the text. We understand that plagiarism,
collusion, and copying are grave and serious offences in the university and accept the
penalties that would be imposed should we engage in plagiarism, collusion or copying.
We understand that any use of AI or AI-assisted tools must be declared fully. We have
read and understood the Assignment Regulations set out in the module documentation.
This assignment, or any part of it, has not been previously submitted by me or any other
person for assessment on this or any other course of study.

Signed: Joshua Corcoran, 25439235, 
        Mathieu Gril, 25441752, 
        Artem Bosyi, 25427377, 
        Matthew Fitzgerald, 25453718
