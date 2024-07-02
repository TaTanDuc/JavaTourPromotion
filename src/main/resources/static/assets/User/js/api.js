const contentElement = document.getElementById("content");
const titleDestinationElement = document.querySelector(".destination_title");
const destinationImageElement = document.getElementById("destination-image");
const AboutMainElement = document.querySelector(".about-main-content");
const blurElement = document.querySelector(".blur-bg");

console.log(AboutMainElement, blurElement);
window.addEventListener("load", function () {
  // Lấy URL hiện tại
  let originalUrl = window.location.href;
  let parts = originalUrl.split("/");
  let idString = parts[parts.length - 1];
  console.log("ID từ URL:", idString);

  // Thực hiện fetch API
  fetch(`http://localhost:8080/api/v1/home/destination/${idString}`)
    .then((response) => {
      if (!response.ok) {
        throw new Error("Network response was not ok " + response.statusText);
      }
      return response.json();
    })
    .then((data) => {
      console.log("Dữ liệu từ API:", data);
      const {
        name,
        provinceName,
        content,
        DoWName,
        cityName,
        imagesList,
        image,
      } = data;
      // Kiểm tra dữ liệu trả về từ API
      let options = "";
      let optionsImage = "";
      // Heading Option
      options += `
                 <div class="content">
                              <div class="blur-bg" style = "background:url(${image})"></div>
                              <h4>Hãy cùng chúng tôi khám phá ${cityName}</h4>
                              <div class="line-dec"></div>
                              <h2>Welcome To ${name}</h2>
                              <p>
                                  Tại đây, Chúng tôi sẽ giới thiệu cho các bạn những địa điểm
                                  đẹp nhất mang đậm bản sắc dân tộc của người Việt Nam.
                              </p>
                </div>`;
      //Image Option
      optionsImage.for += `
                    <div>
                      <div class="owl-stage-outer">
                        <div
                          class="owl-stage"
                          style="
                            transform: translate3d(-1537px, 0px, 0px);
                            transition: all 0.25s ease 0s;
                            width: 4920px;
                          "
                        >
                          <div
                            class="owl-item cloned"
                            style="width: 277.5px; margin-right: 30px"
                          >
                            <div class="item">
                              <div class="thumb">
                                <img
                                  th:src="@{/assets/User/images/cities-01.jpg}"
                                  alt=""
                                />
                                <h4>Havana</h4>
                              </div>
                            </div>
                          </div>
                          <div
                            class="owl-item cloned"
                            style="width: 277.5px; margin-right: 30px"
                          >
                            <div class="item">
                              <div class="thumb">
                                <img
                                  th:src="@{/assets/User/images/cities-02.jpg}"
                                  alt=""
                                />
                                <h4>Kingston</h4>
                              </div>
                            </div>
                          </div>
                          <div
                            class="owl-item cloned"
                            style="width: 277.5px; margin-right: 30px"
                          >
                            <div class="item">
                              <div class="thumb">
                                <img
                                  th:src="@{/assets/User/images/cities-03.jpg}"
                                  alt=""
                                />
                                <h4>George Town</h4>
                              </div>
                            </div>
                          </div>
                          <div
                            class="owl-item cloned"
                            style="width: 277.5px; margin-right: 30px"
                          >
                            <div class="item">
                              <div class="thumb">
                                <img
                                  th:src="@{/assets/User/images/cities-04.jpg}"
                                  alt=""
                                />
                                <h4>Santo Domingo</h4>
                              </div>
                            </div>
                          </div>
                          <div
                            class="owl-item"
                            style="width: 277.5px; margin-right: 30px"
                          >
                            <div class="item">
                              <div class="thumb">
                                <img
                                  th:src="@{/assets/User/images/cities-01.jpg}"
                                  alt=""
                                />
                                <h4>Havana</h4>
                              </div>
                            </div>
                          </div>
                          <div
                            class="owl-item active"
                            style="width: 277.5px; margin-right: 30px"
                          >
                            <div class="item">
                              <div class="thumb">
                                <img
                                  th:src="@{/assets/User/images/cities-02.jpg}"
                                  alt=""
                                />
                                <h4>Kingston</h4>
                              </div>
                            </div>
                          </div>
                          <div
                            class="owl-item active"
                            style="width: 277.5px; margin-right: 30px"
                          >
                            <div class="item">
                              <div class="thumb">
                                <img
                                  th:src="@{/assets/User/images/cities-03.jpg}"
                                  alt=""
                                />
                                <h4>George Town</h4>
                              </div>
                            </div>
                          </div>
                          <div
                            class="owl-item active"
                            style="width: 277.5px; margin-right: 30px"
                          >
                            <div class="item">
                              <div class="thumb">
                                <img
                                  th:src="@{/assets/User/images/cities-04.jpg}"
                                  alt=""
                                />
                                <h4>Santo Domingo</h4>
                              </div>
                            </div>
                          </div>
                          <div
                            class="owl-item active"
                            style="width: 277.5px; margin-right: 30px"
                          >
                            <div class="item">
                              <div class="thumb">
                                <img
                                  th:src="@{/assets/User/images/cities-01.jpg}"
                                  alt=""
                                />
                                <h4>Havana</h4>
                              </div>
                            </div>
                          </div>
                          <div
                            class="owl-item"
                            style="width: 277.5px; margin-right: 30px"
                          >
                            <div class="item">
                              <div class="thumb">
                                <img
                                  th:src="@{/assets/User/images/cities-02.jpg}"
                                  alt=""
                                />
                                <h4>Kingston</h4>
                              </div>
                            </div>
                          </div>
                          <div
                            class="owl-item"
                            style="width: 277.5px; margin-right: 30px"
                          >
                            <div class="item">
                              <div class="thumb">
                                <img
                                  th:src="@{/assets/User/images/cities-03.jpg}"
                                  alt=""
                                />
                                <h4>George Town</h4>
                              </div>
                            </div>
                          </div>
                          <div
                            class="owl-item"
                            style="width: 277.5px; margin-right: 30px"
                          >
                            <div class="item">
                              <div class="thumb">
                                <img
                                  th:src="@{/assets/User/images/cities-04.jpg}"
                                  alt=""
                                />
                                <h4>Santo Domingo</h4>
                              </div>
                            </div>
                          </div>
                          <div
                            class="owl-item cloned"
                            style="width: 277.5px; margin-right: 30px"
                          >
                            <div class="item">
                              <div class="thumb">
                                <img
                                  th:src="@{/assets/User/images/cities-01.jpg}"
                                />
                                <h4>Havana</h4>
                              </div>
                            </div>
                          </div>
                          <div
                            class="owl-item cloned"
                            style="width: 277.5px; margin-right: 30px"
                          >
                            <div class="item">
                              <div class="thumb">
                                <img
                                  th:src="@{/assets/User/images/cities-02.jpg}"
                                  alt=""
                                />
                                <h4>Kingston</h4>
                              </div>
                            </div>
                          </div>
                          <div
                            class="owl-item cloned"
                            style="width: 277.5px; margin-right: 30px"
                          >
                            <div class="item">
                              <div class="thumb">
                                <img
                                  th:src="@{/assets/User/images/cities-03.jpg}"
                                  alt=""
                                />
                                <h4>George Town</h4>
                              </div>
                            </div>
                          </div>
                          <div
                            class="owl-item cloned"
                            style="width: 277.5px; margin-right: 30px"
                          >
                            <div class="item">
                              <div class="thumb">
                                <img
                                  th:src="@{/assets/User/images/cities-04.jpg}"
                                  alt=""
                                />
                                <h4>Santo Domingo</h4>
                              </div>
                            </div>
                          </div>
                        </div>
                      </div>
                      <div class="owl-nav" style="right: 70px">
                        <i
                          class="fa-solid fa-bookmark"
                          style="font-size: 27px; color: black"
                        ></i>
                      </div>
                    </div>
      `;
      AboutMainElement.style.backgroundImage = `url(${image})`;
      contentElement.innerHTML = options;
      titleDestinationElement.textContent = `${cityName}`;
      destinationImageElement.innerHTML = optionsImage;
    })
    .catch((error) => {
      console.error("Lỗi khi fetch dữ liệu:", error);
    });
});
