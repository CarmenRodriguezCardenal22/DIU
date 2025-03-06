// firebase.js
import { initializeApp } from 'firebase/app';
import { getAuth, signInWithEmailAndPassword, createUserWithEmailAndPassword, signOut, fetchSignInMethodsForEmail } from 'firebase/auth';
import { getFirestore, doc, getDoc, setDoc } from 'firebase/firestore';

const firebaseConfig = {
  apiKey: "AIzaSyB1VuH7iaG3f6sAmOSI6O-QTqxB2AP1218",
  authDomain: "test-c552e.firebaseapp.com",
  projectId: "test-c552e",
  storageBucket: "test-c552e.appspot.com",
  messagingSenderId: "25455208624",
  appId: "1:25455208624:web:4c3f579d2fd31b69a4f375",
};

const app = initializeApp(firebaseConfig);
const auth = getAuth(app);
const firestore = getFirestore(app);

export const signIn = async (email, password) => {
  try {
    const userCredential = await signInWithEmailAndPassword(auth, email, password);
    return userCredential.user;
  } catch (error) {
    console.error("Error al iniciar sesión:", error.message);
    if (error.code === "auth/user-not-found") {
      alert("El usuario no existe. Regístrate primero.");
    } else if (error.code === "auth/wrong-password") {
      alert("Contraseña incorrecta.");
    }
    return null;
  }
};

const checkIfEmailExists = async (email) => {
  try {
    const signInMethods = await fetchSignInMethodsForEmail(auth, email);
    return signInMethods.length > 0;  // Si el correo ya está registrado, se retornará true
  } catch (error) {
    console.error("Error checking email:", error);
    return false;  // Si ocurre algún error, tratamos como si no estuviera registrado
  }
};

export const signUp = async (email, password, additionalData) => {
  const emailExists = await checkIfEmailExists(email);

  if (emailExists) {
    console.log("Este correo electrónico ya está registrado.");
    // Mostrar un mensaje de error al usuario o manejar el caso de otra manera.
  } else {
    try {
      await createUserWithEmailAndPassword(auth, email, password);
      console.log("Usuario registrado exitosamente.");
      // Redirigir al usuario o mostrar un mensaje de éxito
    } catch (error) {
      console.error("Error al registrar usuario:", error);
    }
  }
};

export const logOut = async () => {
  try {
    await signOut(auth);
  } catch (error) {
    console.error("Error al cerrar sesión:", error);
  }
};

const generateUserDocument = async (user, additionalData) => {
  if (!user) return;

  const userRef = doc(firestore, `users/${user.uid}`);
  const snapshot = await getDoc(userRef);

  if (!snapshot.exists()) {
    try {
      await setDoc(userRef, {
        email: user.email,
        displayName: additionalData?.displayName || "Usuario",
        photoURL: additionalData?.photoURL || "",
      });
    } catch (error) {
      console.error("Error creando documento de usuario:", error);
    }
  }
};

export { auth, firestore };