**Smart Farm Application** 

Project presentation video

https://youtu.be/U6uWYhKln5g?si=zIVieJBDoEf4Jidf

**Team Members and Roles**
|Name            |ID             | Role                                                                             |
|Ademun Emmanuel |24/2/314/D/361 | Lead Developer| Designed system architecture, core logic, Firebase integration   |
|Kasekende Robert|24/2/314/D/509 |UI/UX Specialist| Designed layouts, user experience, and visual styling           |
|Kamugisha Joram |24/2/314/D/225 |Git & Quality Manager| Managed repository, commits, and code quality              |
|Nakku Annet     |24/2/314/D/011 |QA Engineer| Testing, debugging, and validation of app features                   |
|Nantongo Zainah |24/2/314/D/511 | Documentation Lead| Report writing, research, and project documentation          |


**Project Overview**
The Smart Farm Application is a mobile solution developed to address key agricultural challenges faced by farmers in the Ndejje, Luwero, and surrounding communities. The application provides farmers with access to crop disease guidance, weather updates, and a digital marketplace for agricultural products.

This project was developed as part of the Mobile Programming Capstone Project for BIT 2205.

**Problem Statement**
Farmers in local communities face several challenges, including:
- Limited access to timely weather information
- Difficulty identifying crop diseases and treatments
- Lack of reliable marketplaces for buying and selling agricultural products

The Smart Farm Application aims to solve these problems through a centralized mobile platform.

**Key Features**
Authentication
- User registration and login using Firebase Authentication
- Secure handling of user credentials

Weather Updates
- Real-time weather information using external API
- Helps farmers make informed farming decisions

Disease Detection Support
- Users select crop type and symptoms
- App suggests possible diseases and treatment options

Marketplace
- Farmers can upload and view agricultural products
- Includes product images, descriptions, and pricing

 Image Upload
- Users can upload crop or product images
- Images are stored in Firebase Storage
- Image URLs are managed in the database

**System Architecture**
The application follows a structured Android development approach:
- Language: Kotlin
- Architecture: Activity-based structure (designed for MVVM extension)
- UI Design: XML layouts with Material styling
- Navigation: Multi-activity navigation system
- Backend Services: Firebase

**Technologies Used**
- Kotlin
- Android Studio
- Firebase Authentication
- Firebase Storage
- Firebase Firestore (for storing structured data)
- REST API (for weather data)

Project Structure\com.example.smartfarmapp/
MainActivity.kt
LoginActivity.kt
RegisterActivity.kt
 DashboardActivity.kt
DiseaseActivity.kt
TreatmentActivity.kt
 MarketplaceActivity.kt

res/layout/
 activity_login.xml
activity_register.xml
 activity_dashboard.xml
activity_disease.xml
activity_treatment.xml
activity_marketplace.xml

values/
colors.xml
strings.xml


**Testing Summary**
- Login and registration tested using Firebase Authentication
- Navigation between screens verified
- Marketplace and disease features tested for usability

**QA Summary and test result**
| Test Case         | Description                         |Result|
|-------------------|-------------------------------------|------|
| Login Test        | User logs in with valid credentials | Pass |
| Registration Test | User creates account                | Pass |
| Image Upload      | Upload image to Firebase Storage    | fail |
| Navigation        | Move between screens                | Pass |
| Weather API       | Fetch weather data                  | Pass |
| Invalid Login     | Wrong credentials handled           | Pass |

**Data Privacy and Security**
- User authentication handled securely using Firebase
- No sensitive data stored locally
- Minimal data collection to protect user privacy

**Future Improvements**
- Full implementation of MVVM architecture
- AI-based crop disease detection using image recognition
- Offline support for rural areas
- Push notifications for weather alerts
- Integrated payment system for marketplace

**Installation Guide**
1. Clone the repository:
https://github.com/kamugisha-joram/Mobile_Programming_Group2_KLA_AgricultureProject
2. Open in Android Studio
3. Add your "google-services.json" file in:
app/
4. Sync Gradle
5. Run the application on an emulator or Android device

**Project Timeline**
- Proposal Submission
- MVP Development
- Final Implementation and Testing
- Presentation and Submission

**Conclusion**
The Smart Farm Application demonstrates how mobile technology can be used to solve real-world agricultural challenges. It integrates modern tools such as Firebase and APIs to deliver a practical and scalable solution for farmers.
