# Hospital Appointment System 

## Fetches data from patients and manages appointment and more

List of things I expect the program to do:
- As a user, I should be able to assign myself a seriousness level so that I can get a priority during my appoitment.
- As a user, I want to be able to add my name, age, name of the insurance that I have.
- As a user, I want the application to assign myself a specialist depending on the medical problem that I have.
- As a user, I want tot be able to cancel an appointment.
- As a user, the application should remove the treated patients from the priority list.
- As a user, the application should show me the number of people in queue before.
- As a user, I want the application to assign me a booking id as a scheduling confirmation.
- As a user, I want to be able to cancel the appointment by just using the booking Id.
- As a user, I want to be able to reschedule the appointment to a new future date by just using the booking Id.
- As a user, I want to be able to select a specialist from the hospital provided list of specialists.
- As a user, I want to be able to save patient information while scheduling appointments.
- As a user, I want to be able to load patient information so that I can reschedule appointment.
- As a user, i should be able to cancel an appointment saved.




 
**The application that I am expecting to make can be used in Hospital and medical facilities to keep better track of the patients and manage them in an efficient manner. It should put the patients in a list and use queue to prioritize them depending on the seriousness of the illness. After that it should treat the patients depending on their sequence in the queue. I was thinking of using a priority queue to achieve this goal**

*I came up with the idea for a hospital appointment system after seeing how difficult it can be for patients to manage their appointments, especially in busy hospitals. A family member once had to wait for hours due to a scheduling mix-up, and it made me realize how much smoother things could be with better coordination. I wanted to create something that could help hospitals streamline the process and make it easier for patients to book, reschedule, and manage their appointments without the stress.*

PHASE 3:

# Instructions for End User

- You can start the application by running the SplashScreen class which wil run the background panel and open up the GUI.
- You can add patients in the scheduling system by clcking on the button Patient from the main panel, then clicking add Patient. Then enter the patient information and press enter
- You can add multiple patients the same way to a priority list. All the patients will have a unique booking Id assigned to them.
- You can check the patients sorted in the list by clicking on the "view sorted patients" which will show you a table of patients that has all the patients sorted by emergecy
- You can remove the treated patients from the list by clicking on "Treat next patient button" which will remove the patient at the top of the list which was already been sorted.
- You can also reschedule or cancel a patients appointment by Clicking on the "Patient" button in the main panel and then select "Cancel" or "Reschedule" button.
- When you click the "Cancel" button a new panel will open and then just enter the patient booking Id and it will cancel the patients appointment with that booking id
- When you click the "Reschedule" button a panel will open up and you can enter the booking id and the new appointment date and this will reschedule the patient's appointment to the new added date
- You can move back to main panel from any panel just by clicking "back to main menu" button.
- You can also save the current application status by clicking on the "Save" button.
- You can Load the last saved application staus by clicking on the "Load" button (only works if you have saved previously).
- You can exit the application by clicking on the "Exit" button.
