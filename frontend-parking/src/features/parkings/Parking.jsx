function Parking({ parking, onRemove }) {
  const {
    coordinates,
    address,
    maxSlots,
    _links: {
      self: { href: details },
    },
  } = parking;

  return (
    <li>
      <h2>{address}</h2>
      <p>Coordinates: {coordinates}</p>
      <p>Max Slots: {maxSlots}</p>
      <a href={details} style={{ padding: "20px" }}>
        View Details
      </a>
      <button onClick={onRemove}>remove</button>
    </li>
  );
}

export default Parking;
