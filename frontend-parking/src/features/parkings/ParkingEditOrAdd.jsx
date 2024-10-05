import { useEffect, useState } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";

function ParkingEditOrAdd() {
  const initialState = {
    coordinates: "",
    address: "",
    maxSlots: "",
  };

  const [parking, setParking] = useState(initialState);
  const navigate = useNavigate();
  const { id } = useParams();

  useEffect(() => {
    if (id !== "new") {
      fetch(`/api/parkings/${id}`)
        .then((res) => res.json())
        .then((data) => setParking(data));
    }
  }, [id]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setParking({ ...parking, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    const url = `/api/parkings${id !== "new" ? `/${id}` : ""}`;
    try {
      const res = await fetch(url, {
        method: id !== "new" ? "PUT" : "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(parking),
      });

      if (!res.ok) {
        const errorData = res.json();
        console.error(errorData);
        return;
      }

      setParking(initialState);
      navigate("/parkings");
    } catch (error) {
      console.error("There was problem with POST or PUT operation", error);
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <div>
        <label htmlFor="coordinates">Coordinates</label>
        <input
          type="text"
          name="coordinates"
          onChange={handleChange}
          value={parking.coordinates || ""}
        />
      </div>
      <div>
        <label htmlFor="address">Address</label>
        <input
          type="text"
          name="address"
          onChange={handleChange}
          value={parking.address || ""}
        />
      </div>
      <div>
        <label htmlFor="maxSlots">Maximum number of slots</label>
        <input
          type="text"
          name="maxSlots"
          onChange={handleChange}
          value={parking.maxSlots || ""}
        />
      </div>
      <div>
        <button type="submit">save</button>
        <Link to="/parkings">cancel</Link>
      </div>
    </form>
  );
}

export default ParkingEditOrAdd;
