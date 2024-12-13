function AnimalList() {
    const animals = [
        {
            id: 1,
            name: "dog"
        },
        {
            id: 2,
            name: "cat"
        },
        {
            id: 3,
            name: "bird"
        },
    ];

    const HTMLAnimals = animals.map((animal) =>{
        return (
            <li key={animal.id}>
                <h3>{animal.name}</h3>
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