import Vue from 'vue'
import Router from 'vue-router'
import Home from '../components/Home'

import AppIndex from '../components/home/AppIndex'
import Login from '../components/Login'
import Register from '../components/Register'
import GoodsIndex from '../components/goods/GoodsIndex'
import GoodDetails from '../components/goods/GoodDetails'
import Cart from '../components/goods/Cart'

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/home',
      name: 'Home',
      component: Home,
      // home页面并不需要被访问
      redirect: '/index',
      children: [
        {
          path: '/index',
          name: 'AppIndex',
          component: AppIndex,
          meta: {
            requireAuth: true
          }
        },
        {
          path: '/goods',
          name: 'GoodsIndex',
          component: GoodsIndex,
          meta: {
            requireAuth: true
          }
        },
        {
          path: '/goods/goodsDetail',
          name: 'GoodDetails',
          component: GoodDetails,
          meta: {
            requireAuth: true
          }
        },
        {
          path: '/cart',
          name: 'Cart',
          component: Cart,
          meta: {
            requireAuth: true
          }
        }
      ]
    },
    {
      path: '/login',
      name: 'Login',
      component: Login
    },
    {
      path: '/register',
      name: 'Register',
      component: Register
    },
    {
      path: '/admin',
      name: 'Admin',
      component: () => import('../components/admin/AdminIndex'),
      meta: {
        requireAuth: true
      }
    }
  ]
})
