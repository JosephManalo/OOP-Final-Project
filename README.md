## FitFlow: Gym Management System

## OOP Project Documentation

Table of Contents

* [Project Overview](#project-overview)

* [Features](#features)

* [OOP Implementation](#oop-implementation)

* [SDG Integration](#sdg-integration)

### Project Overview

FitFlow is a **Gym Management System** is designed to streamline and automate the administrative tasks of a gym. It enables gym owners to efficiently manage memberships, process payments, and track member information, while offering users easy access to their membership details and trainer interactions. With a user-friendly interface, FitFlow ensures smooth operations for both admins and members, making gym management hassle-free.

### Features:

- Member Management
- Membership Management
- Trainer Management
- Payment Management
- Admin Functionality
- User Functionality
- Login System
- Trainer Selection(Premium Members)
- Data Storage and Tracking

### OOP Implementation

The Gym Management System has been designed with a focus on **Object-Oriented Programming (OOP)** principles, which ensure modularity, maintainability, and scalability of the system. Below is a detailed analysis of how each key OOP concept is implemented:

*1. Encapsulation:*

> Classes such as Member, Trainer, and Membership encapsulate data such as names, contact details, membership ID, and payment status. These classes provide methods to safely access and modify these attributes, such as getName(), getMembershipId(), and pay().
> This approach ensures that data is protected from unauthorized external access and can only be modified through well-defined methods.


*2. Abstraction:*

> Classes such as Member, Trainer, and Membership abstract the essential characteristics of the respective entities. For example, a Member object abstracts the complexities of how member details are stored and accessed, while providing simple methods for interactions like viewMembershipDetails() or processPayment().
> Similarly, the Membership class hides the intricacies of membership fee calculations or payment status checks, exposing only the necessary functions.

*3. Polymorphism:*

> The toString() method is overridden in classes like Member, Trainer, and Membership to provide unique string representations for each object.Member objects return their details like name, membership ID, and age. Trainer objects return details such as name, age, and specialization. Membership objects return detailed membership information including the membership type (e.g., Basic or Premium), duration, and trainer assigned (if applicable).
> This method allows us to treat Member, Trainer, and Membership objects uniformly, while still ensuring that each type provides its own specialized data when needed.

### SDG Integration

- **Promoting Health and Well-being (SDG 3)**: FitFlow encourages physical activity and fitness by enabling members to track their progress, stay motivated, and achieve their health goals. It supports gyms in promoting healthier lifestyles through memberships, personalized trainer assignments, and performance tracking.
- **Decent Work and Economic Growth (SDG 8)**: By streamlining gym operations, FitFlow can help gym owners reduce administrative costs and focus on expanding their business, potentially leading to job creation for trainers and staff, contributing to local economies.
