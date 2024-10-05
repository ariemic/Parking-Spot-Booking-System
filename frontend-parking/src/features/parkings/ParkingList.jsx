import { useEffect, useState } from "react";
import Parking from "./Parking";
import { Link } from "react-router-dom";

function ParkingList() {
  const [parkings, setParkings] = useState([]);
  const [loading, setLoading] = useState(false);

  const removeParking = async (id) => {
    try {
      const response = await fetch(`/parkings/${id}`, {
        method: "DELETE",
        headers: {
          Accept: "application/json",
          "Content-Type": "application/json",
        },
      });

      if (!response.ok) {
        throw new Error(`Faild to delete parking with ID ${id}`);
      }

      const updatedParkings = [...parkings].filter((p) => p.id !== id);
      setParkings(updatedParkings);
    } catch (error) {
      console.error(
        `There are booking for ${id} parking, you need to remove them first!`,
        error
      );
    }
  };

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
    <>
      <div>
        <h1>Parkings</h1>
        <Link to="/parkings/new">Add parking</Link>
      </div>
      <ul>
        {parkings.map((parking, idx) => {
          return (
            <Parking parking={parking} onRemove={removeParking} key={idx} />
          );
        })}
      </ul>
    </>
  );
}

export default ParkingList;
