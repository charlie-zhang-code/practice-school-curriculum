<script setup lang="ts">

</script>

<template>
  <div class="flex flex-col">
    <div class="bg-white fixed top-0 left-0 z-999 w-full flex items-center h-16 p-x-6 my-container-min-width">
      <Header />
    </div>
    <div class="h-20"/>
    <main class="my-container">
      <router-view />
    </main>
    <div class="h-20 flex items-center justify-center p-6">
      <Footer />
    </div>
  </div>
</template>

<style scoped></style>
