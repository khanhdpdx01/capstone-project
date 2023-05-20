<template>
  <div>
    <div
      @drop.prevent="addFile($event)"
      @dragover.prevent="dragover($event)"
      @click.self="clickOnInput"
      class="cursor-pointer hover:bg-gray-50 mt-1 flex justify-center px-6 pt-5 pb-6 border-2 border-gray-300 border-dashed rounded-md"
    >
      <div @click.self="clickOnInput" class="space-y-1 text-center">
        <svg
          class="mx-auto h-12 w-12 text-gray-400"
          stroke="currentColor"
          fill="none"
          viewBox="0 0 48 48"
          aria-hidden="true"
        >
          <path
            d="M28 8H12a4 4 0 00-4 4v20m32-12v8m0 0v8a4 4 0 01-4 4H12a4 4 0 01-4-4v-4m32-4l-3.172-3.172a4 4 0 00-5.656 0L28 28M8 32l9.172-9.172a4 4 0 015.656 0L28 28m0 0l4 4m4-24h8m-4-4v8m-12 4h.02"
            stroke-width="2"
            stroke-linecap="round"
            stroke-linejoin="round"
          />
        </svg>
        <div class="flex text-sm text-gray-600">
          <label
            :for="this.name"
            class="relative cursor-pointer bg-white rounded-md font-medium text-blue-500 hover:text-blue-600 transition-color duration-300"
          >
            <span>Upload a file</span>
            <input
              multiple
              :id="this.name"
              :name="this.name"
              type="file"
              @change="choiceFile"
              class="sr-only"
            />
          </label>
          <p class="pl-1">or drag and drop</p>
        </div>
        <p class="text-xs text-gray-500">PNG, JPG, GIF up to 10MB</p>
      </div>
    </div>

    <div
      v-for="(item, index) in this[this.name]"
      style="width: 100px; height: 100px"
      class="relative mt-3 ml-3 border-2 shadow-xl rounded-md inline-block"
      :key="index"
    >
      <!--      preview images     -->
      <template v-if="item.type == 'image/png'">
        <img class="object-cover w-full h-full" :src="item.url" />
        <img
          class="absolute top-0 left-0 cursor-pointer w-8 h-8"
          src="../../assets/img/trash.svg"
          alt="Remove icon"
          @click="removeFile(item)"
        />
      </template>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    name: {
      type: String,
      required: true,
    }
  },
  data() {
    return {
      [this.name]: [],
    };
  },
  methods: {
    dragover: function (e) {
      e.stopPropagation();
    },
    async addFile(e) {
      e.stopPropagation();
      let droppedFiles = e.dataTransfer.files;
      if (!droppedFiles) return;

      [...droppedFiles].forEach((f) => {
        this[this.name].push(f);
        localStorage.setItem(
          this.name,
          JSON.stringify(this.stringifyFileObject(this[this.name]))
        );
      });
    },
    clickOnInput() {
      const uploadInput = document.getElementById(this.name);
      uploadInput.click();
    },
    removeFile(file) {
      URL.revokeObjectURL(file.url);
      this[this.name] = this[this.name].filter((f) => {
        return f != file;
      });
      localStorage.setItem(
        this.name,
        JSON.stringify(this.stringifyFileObject(this[this.name]))
      );
    },
    choiceFile(e) {
      [...e.target.files].forEach(async (f) => {
        f.url = URL.createObjectURL(f);
        this[this.name].push(f);
      });

      localStorage.setItem(
        this.name,
        JSON.stringify(this.stringifyFileObject(this[this.name]))
      );
    },
    stringifyFileObject(files) {
      const arrData = files.map((file) => ({
        name: file.name,
        type: file.type,
        url: file.url,
      }));
      return arrData;
    },
  },
};
</script>

<style></style>
