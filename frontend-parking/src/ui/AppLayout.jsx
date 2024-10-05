import { Outlet } from "react-router-dom";

function AppLayout() {
  return (
    <div>
      <header>
        <h1>Parking booking system</h1>
      </header>
      <main>
        <Outlet />
      </main>
    </div>
  );
}

export default AppLayout;
