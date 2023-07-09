/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./public/**/*.html",
    "./src/**/*.{vue,js,ts,jsx,tsx}",
    "./node_modules/flowbite/**/*.js",
  ],
  theme: {
    extend: {},
    // screens: {
    //   sm: { max: "639px" },
    // },
  },
  plugins: [require("flowbite/plugin")],
};
