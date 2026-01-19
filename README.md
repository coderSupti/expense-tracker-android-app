# 💰 Expense Tracker Android App
  
A comprehensive personal finance management application for Android built with Java. This app helps users track their daily expenses, manage income, and analyze spending habits with intuitive charts and cloud synchronization.

## 📱 Features

*   **📊 Dashboard**: Real-time overview of your current month's total income, expenses, and current balance.
*   **💸 Transaction Tracking**: Easily add, edit, and delete daily transactions.
    *   Support for both **Income** and **Expense** entries.
    *   🏷️ Categorizations (Food, Transport, Shopping, Bills, Salary, Investment, etc.).
    *   📝 Add notes and custom dates to transactions.
*   **☁️ Cloud Sync & Backup**:
    *   Seamless synchronization with **Firebase Firestore**.
    *   Access your data across multiple devices.
    *   Offline-first architecture using **Room Database**.
*   **🔐 Authentication**:
    *   Secure Email/Password login via Firebase Auth.
    *   **👤 Guest Mode** for trying out the app without registration.
*   **🔍 Smart Filtering**: Filter transactions by daily, weekly, monthly, or yearly views.
*   **📈 Analytics**: Visual breakdown of expenses by category (Powered by MPAndroidChart).
*   **🎨 User Interface**: Modern, clean UI built with Material Design components.

## 🛠️ Tech Stack

*   **Language**: Java
*   **Minimum SDK**: 24 (Android 7.0 Nougat)
*   **Architecture**: MVVM (Model-View-ViewModel)
*   **Local Database**: Room Persistence Library
*   **Backend / Cloud**: Firebase Authentication, Firebase Firestore
*   **UI Components**: Material Design, XML Layouts, Bottom Sheets

## 🚀 Getting Started

### 📦 Prerequisites

*   **Android Studio Ladybug** or newer
*   **JDK 11** or higher
*   A **Firebase Project**

### ⚙️ Installation

1.  **Clone the repository**
    ```bash
    git clone https://github.com/coderSupti/expense-tracker-android-app.git
    ```

2.  **Open in Android Studio**
    *   Launch Android Studio.
    *   Select `Open` and navigate to the project directory.

3.  **🔥 Firebase Setup (Crucial)**
    *   Go to [Firebase Console](https://console.firebase.google.com/).
    *   Create a new project.
    *   Add an Android app with package name: `com.example.expensetrackerapp`.
    *   Download the `google-services.json` file.
    *   **Move `google-services.json`** into the `app/` directory of the project.
    *   Enable **Authentication** (Email/Password provider).
    *   Enable **Cloud Firestore** (Start in Test Mode or configure proper security rules).

4.  **▶️ Build & Run**
    *   Let Gradle sync the project dependencies.
    *   Connect a device or start an emulator.
    *   Click **Run** (Green Play Button).

## 📂 Project Structure

```
com.example.expensetrackerapp
├── auth/           # Login, Register, Forgot Password
├── data/
│   ├── local/      # Room Database, DAOs, Entities
│   └── repository/ # Repositories handling data sync
├── ui/
│   ├── dashboard/  # Home screen logic
│   ├── transactions/ # Add/Edit transaction screens
│   ├── analytics/  # Charts and stats
│   └── profile/    # User settings
└── utils/          # Helper classes (Currency, Date, Constants)
```
