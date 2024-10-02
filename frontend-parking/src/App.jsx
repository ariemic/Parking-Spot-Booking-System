import { useEffect } from "react";
import { useState } from "react";

function App() {
  const [parkings, setParkings] = useState([]);
  const [loading, setLoading] = useState(false);

  // const { coordinates, address, maxSlots} = parking;

  useEffect(() => {
    setLoading(true);

    fetch("/parkings")
      .then((res) => {
        if (!res.ok) {
          throw new Error("network response is not ok");
        }
        return res.json();
      })
      .then((data) => {
        console.log(data);
        setParkings(data._embedded.parkings);

        setLoading(false);
      })
      .catch((err) =>
        console.error("There is problem with fetching parkings", err)
      );
  }, []);

  if (loading) {
    return <p>Loading...</p>;
  }

  return (
    <div>
      <h1>Parkings</h1>
      <ul>
        {parkings.map((parking, index) => (
          <li key={index}>
            <h2>{parking.address}</h2>
            <p>Coordinates: {parking.coordinates}</p>
            <p>Max Slots: {parking.maxSlots}</p>
            <a href={parking._links.self.href}>View Details</a>
          </li>
        ))}
      </ul>
    </div>
  );
}

export default App;
