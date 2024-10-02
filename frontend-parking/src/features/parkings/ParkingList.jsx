import Parking from "./Parking";

function ParkingList({ parkings, onRemove }) {
  return (
    <ul>
      {parkings.map((parking, idx) => {
        return <Parking parking={parking} onRemove={onRemove} key={idx} />;
      })}
    </ul>
  );
}

export default ParkingList;
