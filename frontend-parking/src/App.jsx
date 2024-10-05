import { createBrowserRouter, RouterProvider } from "react-router-dom";
import ParkingList from "./features/parkings/ParkingList";

import ParkingEditOrAdd from "./features/parkings/ParkingEditOrAdd";
import AppLayout from "./ui/AppLayout";
import Error from "./ui/Error";

const router = createBrowserRouter([
  {
    path: "/",
    element: <AppLayout />,
    errorElement: <Error />,
    children: [
      {
        path: "parking",
        element: <ParkingList />,
      },
      {
        path: "parking/new",
        element: <ParkingEditOrAdd />,
      },
    ],
  },
]);

function App() {
  return <RouterProvider router={router} />;
}

export default App;
