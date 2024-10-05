function Parking({ parking, onRemove }) {
  // const [parking, setParking] = useState({});

  //   const addParking = async => {
  //   const response = await fetch("/parkings/", {
  //     method: "POST",
  //     body: JSON.stringify(parking)
  //   })
  // }

  const {
    _links: {
      self: { href: details },
    },
  } = parking;

  function extractId() {
    if (!details) return;

    const parts = details.split("/");
    const id = parts[parts.length - 1];
    // console.log(id);
    return id;
  }

  const parkingWithId = {
    ...parking,
    id: extractId(),
  };

  const { coordinates, address, maxSlots, id } = parkingWithId;

  return (
    <li>
      <h2>{address}</h2>
      <p>Coordinates: {coordinates}</p>
      <p>Max Slots: {maxSlots}</p>
      <a href={details} style={{ padding: "20px" }}>
        View Details
      </a>
      <button onClick={() => onRemove(id)}>remove</button>
    </li>
  );
}

export default Parking;
