# Custom ArrayList Implementation in Java

## 📌 Overview
This repository contains a custom implementation of Java’s `ArrayList` built completely **from scratch using core Java.  
The goal of this project is to understand the **internal working of dynamic arrays, rather than just using the built-in Java Collection Framework.

This implementation closely mimics the behavior of Java’s `ArrayList`, including dynamic resizing, element shifting, iteration, cloning, and capacity management.

---

## 🎯 Objectives
- Understand how `ArrayList` works internally
- Implement dynamic array resizing
- Practice index-based insertion and deletion
- Gain confidence in Java Collections for interviews
- Build a strong portfolio project using core Java

---

## ✨ Features Implemented

### 🔹 Core Operations
- `add(E element)`
- `add(int index, E element)`
- `get(int index)`
- `set(int index, E element)`
- `remove(int index)`
- `remove(Object o)`
- `removeFirst()`
- `removeLast()`
- `clear()`
- `size()`
- `isEmpty()`

### 🔹 Search Operations
- `contains(Object o)`
- `indexOf(Object o)`
- `lastIndexOf(Object o)`

### 🔹 Capacity Management
- `ensureCapacity(int capacity)`
- `trimToSize()`
- Dynamic resizing (1.5x growth strategy)

### 🔹 Utility & Advanced Methods
- `toArray()`
- `toArray(T[] a)`
- `subList(int from, int to)`
- Custom `Iterator` support
- `clone()` (deep copy)
- `equals()` and `hashCode()`
- Custom exceptions for error handling

---

## 🧠 Internal Working (How It Works)

- Uses a **generic array (`E[]`)** internally
- Automatically **resizes** when capacity is reached
- Elements are **shifted manually** during insertion and deletion
- Supports **enhanced for-loop** via `Iterable`
- Custom exceptions ensure **safe index access**

---

## 🧪 Driver Program
A dedicated driver class (`MyArrayListDriver`) is included to test:
- All add/remove operations
- Searching and indexing
- Iteration using enhanced for-loop
- Cloning and equality comparison
- Conversion to arrays

This ensures the implementation is fully tested and validated.

---


   ```bash
   git clone https://github.com/your-username/your-repo-name.git
