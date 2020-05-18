# VetSystem

### 1.Opis aplikacji

VetSystem jest propozycją prostego systemu weterynaryjnego opartego o REST API.

Do stworzenia wartswy widkou została wykorzystana biblioteka Vaadin w wersji '14.1.27'.

### 2.Budowa aplikacji

Warstwa frontendowa oraz backendowa zostały utworzone jako osobne projekty a w celu ich połaczenia wykorzystano klasę RestTemplate.

Dodatkowo w projekcie wykorzystano dwa zewnętrzne źródła danych:

- konwerter jednostek w celu przeliczenia jednostek wprowadzanych do bazy leków

- usługę wysyłania sms  w celu powiadomieniu klienta o zbliżającym się terminie ponownego szczepienia

### 3.Linki aplikacji
aplikacja backendowa: https://github.com/MajaBrog/VetSystem.git

aplikacja frontendowa: https://github.com/MajaBrog/VetSystem_frontend.git
