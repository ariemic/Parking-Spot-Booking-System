import { useEffect } from "react";
import { useState } from "react";
import ParkingList from "./features/parkings/ParkingList";

function App() {
  const [parkings, setParkings] = useState([]);
  const [loading, setLoading] = useState(false);

  const removeParking = async (id) => {
    await fetch(`/parkings/${id}`, {
      method: "DELETE",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
      },
    }).then(() => {
      let updatedParkings = [...parkings].filter((p) => p.id !== id);
      setParkings(updatedParkings);
    });
  };

  // const addParking = async => {
  //   await fetch("/parkings/", {
  //     method: "POST",
  //     headers: {
  //       Accept: "application/json",
  //       "Content-Type": "application/json",
  //     },
  //     body: JSON.stringify(parking),
  //   })
  // }

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
      <h1>Parkingi</h1>
      <ParkingList parkings={parkings} onRemove={removeParking} />
    </div>
  );
}

export default App;
