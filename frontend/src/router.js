// import Home from './views/Home.vue'
import Create from './views/CreateMap.vue'

const Home = { template: '<p>home page</p>' }
const routers = [
  {
    path: '/home',
    name: 'home',
    component: Home
  },
  {
    path: '/create',
    name: 'create',
    component: Create
  },
  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ './views/About.vue')
  }
]
export default routers
