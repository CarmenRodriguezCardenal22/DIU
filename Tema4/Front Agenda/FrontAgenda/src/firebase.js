import {initializeApp}  from 'firebase/app';
import {getAuth, GoogleAuthProvider, signInWithPopup} from 'firebase/auth';
import {getFirestore, doc, getDoc, setDoc} from 'firebase/firestore';

// Configuración de Firebase
const firebaseConfig = {
    apiKey: "AIzaSyB1VuH7iaG3f6sAmOSI6O-QTqxB2AP1218",
    authDomain: "test-c552e.firebaseapp.com",
    projectId: "test-c552e",
    storageBucket: "test-c552e.appspot.com",
    messagingSenderId: "25455208624",
    appId: "1:25455208624:web:4c3f579d2fd31b69a4f375",
  };
  
  // Inicializar Firebase
  const app = initializeApp(firebaseConfig);
  const auth = getAuth(app);
  const firestore = getFirestore(app);
  
  const provider = new GoogleAuthProvider();
  
  // Función para iniciar sesión con Google
  export const signInWithGoogle = () => {
    signInWithPopup(auth, provider)
      .then((result) => {
        console.log("Usuario autenticado:", result.user);
      })
      .catch((error) => {
        console.error("Error en la autenticación con Google:", error);
      });
  };
  
  // Generar documento de usuario en Firestore
  export const generateUserDocument = async (user, additionalData) => {
    if (!user) return null;
  
    const userRef = doc(firestore, `users/${user.uid}`);
    const snapshot = await getDoc(userRef);
  
    if (!snapshot.exists()) {
      const { email, displayName, photoURL } = user;
      try {
        await setDoc(userRef, {
          displayName,
          email,
          photoURL,
          ...additionalData,
        });
      } catch (error) {
        console.error("Error creando el documento de usuario:", error);
      }
    }
  
    return getUserDocument(user.uid);
  };
  
  // Obtener documento de usuario desde Firestore
  const getUserDocument = async (uid) => {
    if (!uid) return null;
  
    try {
      const userRef = doc(firestore, `users/${uid}`);
      const userDocument = await getDoc(userRef);
  
      return userDocument.exists() ? { uid, ...userDocument.data() } : null;
    } catch (error) {
      console.error("Error obteniendo el usuario:", error);
    }
  };
  
  export { auth, firestore };