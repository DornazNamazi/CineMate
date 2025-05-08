# 🎬 CineMate

**CineMate** is a mobile cinema ticket booking app for Android. It improves the movie-going experience by allowing users to browse cinemas and movies, select showtimes, reserve seats, and manage bookings — all from one intuitive interface. Built using **Java** and **Firebase**, CineMate delivers seamless interactions for both customers and administrators with a focus on convenience, flexibility, and performance.

---

## 🚀 Features

### 👤 Customer
- Create account and log in
- Browse cinema and movie listings
- Choose cinema, movie, showtime, and seats
- Make and cancel bookings
- View and manage personal bookings and profile

### 🛠️ Administrator
- Log in with credentials
- Add, update, delete movies and cinemas
- View and manage all bookings
- Update personal information

---

# 💻 Tech Stack

## 📱 Frontend (Android)
![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)
![XML](https://img.shields.io/badge/XML-UI-blue?style=for-the-badge&logo=w3c&logoColor=white)

## 🔥 Firebase
![Firestore](https://img.shields.io/badge/Firestore-ffa611?style=for-the-badge&logo=firebase&logoColor=white)
![Firebase Auth](https://img.shields.io/badge/Firebase_Auth-F57C00?style=for-the-badge&logo=firebase&logoColor=white)
![Firebase Storage](https://img.shields.io/badge/Firebase_Storage-FFC400?style=for-the-badge&logo=firebase&logoColor=white)

---

# 🧠 Architecture Overview

## 🎨 Presentation Layer
- `MainActivity`, `IntroPages`, `LoginPage`, `SignUpPage`
- `AdminDashboard`, `AddMovieActivity`, `UpdateMovieActivity`

## ⚙️ Business Logic Layer
- `User` (abstract), `Admin`, `Customer`
- `MovieFactory`, `TicketBuilder`, `Ticket`
- `Booking`, `BookingFacade`
- `CustomerRegistration`, `UserRegistrationTemplate`

## 🗄️ Data Access Layer
- `DatabaseHelper` (Singleton)
- Firebase integration: Auth, Firestore
- Secure, abstracted data operations

---

# 🧩 Design Patterns Used

- 🧱 **Singleton**  
- 🏭 **Factory**  
- 🧾 **Builder**  
- 👀 **Observer**  
- 🧰 **Facade**  
- 🧬 **Template**  
- 🧠 **Strategy**  
- 🎮 **Command**  
- 🔌 **Adapter**  
- 🧺 **ViewHolder**  
- 💅 **Decorator**

---

# 📊 Project Stats

| Metric               | Value                            |
|----------------------|----------------------------------|
| 🧑‍💻 Main Language     | ![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white) |
| 🔥 Total Commits     | `TBD`                            |
| 🗂️ Files & Folders    | `~20 files`, multiple packages   |
| 📦 Dependencies       | Firebase, Google Play Services   |
| ⏳ Development Time   | ~2–3 weeks                       |

---

# 📚 Top Languages Used in This Project

![Java](https://img.shields.io/badge/Java-90%25-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![XML](https://img.shields.io/badge/XML-10%25-005FAD?style=for-the-badge&logo=w3c&logoColor=white)

---

# 👥 Team Members

- [**Arshia Salehi**](https://github.com/arshiasalehi)
- [**Daiane Flores de Oliveira**](https://github.com/daianefloresdeoliveira)
- [**Dornaz Namazi**](https://github.com/DornazNamazi)

---

[![](https://visitcount.itsvg.in/api?id=CineMateApp&label=Views&color=blueviolet&icon=5)](https://visitcount.itsvg.in)
