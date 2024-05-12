import Login from "../pages/utlis/Login";
import Registration from "../pages/utlis/Registration";
import ChooseCompanyPage from "../pages/awardobe/ChooseCompanyPage";
import ChooseBranchPage from "../pages/awardobe/ChooseBranchPage";
import ChooseAgrPage from "../pages/awardobe/ChooseAgrPage";
import PerformAutomativeWardrobe from "../pages/awardobe/PerformAutomativeWardrobe";
import CompaniesPage from "../pages/admin/CompaniesPage/CompaniesPage";
import BranchesPage from "../pages/admin/BranchesPage/BranchesPage";
import AgrsPage from "../pages/admin/AgrsPage/AgrsPage";
import CellsPage from "../pages/admin/CellsPage";
import UserPage from "../pages/utlis/UserPage/UserPage";

export const adminRoutes = [
    { path: "/admin/companies", element: <CompaniesPage />, exact: true },
    { path: "/admin/branches", element: <BranchesPage />, exact: true },
    { path: "/admin/agrs", element: <AgrsPage />, exact: true },
    { path: "/admin/cells", element: <CellsPage />, exact: true },
    { path: '/user', element: <UserPage />, exact: true  },
]

export const executorRoutes = [
    { path: "/company", element: <BranchesPage />, exact: true },
    { path: '/agr/:id', element: <PerformAutomativeWardrobe />, exact: true  },
    { path: '/user', element: <UserPage />, exact: true  }
]

export const userRoutes = [
    { path: '/companies', element: <ChooseCompanyPage />, exact: true  },
    { path: '/companies/:id', element: <ChooseBranchPage />, exact: true  },
    { path: '/branches/:id', element: <ChooseAgrPage />, exact: true  },
    { path: '/agr/:id', element: <PerformAutomativeWardrobe />, exact: true  },
    { path: '/user', element: <UserPage />, exact: true  },
]

export const publicRoutes = [
    { path: '/login', element: <Login />, exact: true  },
    { path: '/registration', element: <Registration />, exact: true  },
]