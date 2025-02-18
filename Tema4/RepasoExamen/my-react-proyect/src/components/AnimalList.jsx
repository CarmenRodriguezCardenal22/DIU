function AnimalList() {
  const animals = [
    {
        id: 1,
        name: "dog",
        img: "https://upload.wikimedia.org/wikipedia/commons/8/8a/Golden_Retriever_9-year_old.jpg"
    },
    {
        id: 2,
        name: "cat",
        img: "https://static.nationalgeographic.es/files/styles/image_3200/public/nationalgeographic_1468962.jpg?w=1600&h=1179"
    },
    {
        id: 3,
        name: "bird",
        img: "https://s.libertaddigital.com/2023/07/04/blue-wire-patrick-dpa-pajaro-azul-cordon.jpg"
    },
  ];

  const HTMLAnimals = animals.map((animal) => {
    return (
        <li key={animal.id}>
            <h3>{animal.name}</h3>
            <img src={animal.img} alt="animal picture" width={200}/>
        </li>
    )
  })

  return (
    <section>
        <h2>Animals:</h2>
        <ul>
            {HTMLAnimals}
        </ul>
    </section>
  )
}

export default AnimalList