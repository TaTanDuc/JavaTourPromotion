const contentElement = document.getElementById("content");
const titleDestinationElement = document.querySelector(".destination_title");
const destinationImageElement = document.querySelector(".owl-cites-town");
const AboutMainElement = document.querySelector(".about-main-content");
const blurElement = document.querySelector(".blur-bg");
const owlItems = document.querySelectorAll(".owl-item");
const stars = document.querySelectorAll(".start_item");
const ratingText = document.querySelector(".rating-text");
const btnRatingElement = document.querySelector(".btn-Rating");
const contentDestinationElement = document.querySelector(
  "#content-destination"
);
const bestLocationElement = document.querySelector("#best-image");
let selectedRating = -1;
let valueRating;
let index = 0;
let temp;
let idString;
let originalUrl = window.location.href;
let parts = originalUrl.split("/");
idString = parts[parts.length - 1];
const listCommentElement = document.querySelector(".list_comment");
if(idString){
    $(document).ready(function () {
     console.log(idString)
            $.ajax({
              url: `http://localhost:8080/api/v1/home/destination/${idString}`,
              type: "GET",
              dataType: "json",
              success: function (data) {
                let options ='';
                $.each(data.commentsList, function (i, item) {
                console.log(item)

                  options += `<li class="comment_item" style="display: flex; gap: 20px">
                                                <img
                                                  src="/assets/User/images/image-VịnhHạLong-01.jpg"
                                                  alt=""
                                                  class="image_comment"
                                                  style="width: 50px; height: 50px; border-radius: 50%"
                                                />
                                                <div class="comment_info">
                                                  <span> ${item.username}</span>
                                                  <p>${item.content}</p>
                                                </div>
                                              </li>`;
                });
                $(".list_comment").html(options);
              },
              error: function (xhr, status, error) {
                console.error("Error fetching data: ", status, error);
              },
            });
          });
}
window.addEventListener("load", function () {
  // Lấy URL hiện tại
  let originalUrl = window.location.href;
  let parts = originalUrl.split("/");
  idString = parts[parts.length - 1];

  // Thực hiện fetch API
  fetch(`http://localhost:8080/api/v1/home/destination/${idString}`)
    .then((response) => {
      if (!response.ok) {
        throw new Error("Network response was not ok " + response.statusText);
      }
      return response.json();
    })
    .then((data) => {
      const {
        name,
        provinceName,
        content,
        DoWName,
        cityName,
        destinationsImagesList,
        image,
      } = data;
      const { Path: Path1 } = destinationsImagesList[0];
      const { Path: Path2 } = destinationsImagesList[1];
      const { Path: Path3 } = destinationsImagesList[2];
      const { Path: Path4 } = destinationsImagesList[3];
      console.log(Path1, Path2);
      // Kiểm tra dữ liệu trả về từ API
      let options = "";
      let optionsImage = "";
      let optionsBestImage = "";
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
      optionsImage += ` <div>
                      <div class="owl-stage-outer"><div
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
                                src="${Path1}"
                                alt=""
                                style ="height: 209px; object-fit: cover"
                              />
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
                                src="${Path2}"
                                alt=""
                                style ="height: 209px; object-fit: cover"
                              />
      
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
                                src="${Path4}"
                                alt=""
                                           style ="height: 209px; object-fit: cover"
                              />

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
                                src="${Path1}"
                                alt=""
                                           style ="height: 209px; object-fit: cover"
                              />
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
                                src="${Path2}"
                                alt=""
                                           style ="height: 209px; object-fit: cover"
                              />
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
                                src="${Path4}"
                                alt=""
                                           style ="height: 209px; object-fit: cover"
                              />
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
                                src="${Path1}"
                                alt=""
                                           style ="height: 209px; object-fit: cover"
                              />
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
                                src="${Path2}"
                                alt=""
                                           style ="height: 209px; object-fit: cover"
                              />
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
                                src="${Path4}"
                                alt=""
                                           style ="height: 209px; object-fit: cover"
                              />
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
                                src="${Path1}"
                                alt=""
                                           style ="height: 209px; object-fit: cover"
                              />
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
                                src="${Path2}"
                                alt=""
                                           style ="height: 209px; object-fit: cover"
                              />
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
                                src="${Path4}"
                                alt=""
                                           style ="height: 209px; object-fit: cover"
                              />
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
                                src="${Path1}"
                                           style ="height: 209px; object-fit: cover"
                              />
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
                                src="${Path2}"
                                alt=""
                                           style ="height: 209px; object-fit: cover"
                              />
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
                                src="${Path4}"
                                alt=""
                                           style ="height: 209px; object-fit: cover"
                              />
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
                                src="${Path1}"
                                alt=""
                                style ="height: 209px; object-fit: cover"
                              />
                            </div>
                          </div>
                        </div>
                      </div></div>
                      <div class="owl-nav">
                        <button
                          type="button"
                          role="presentation"
                          class="owl-prev"
                        >
                          <span aria-label="Previous">‹</span></button
                        ><button
                          type="button"
                          role="presentation"
                          class="owl-next"
                        >
                          <span aria-label="Next">›</span>
                        </button>
                      </div>
                      <div class="owl-dots disabled"></div>
                    </div>`;
      optionsBestImage += `<div class="options">
                <div
                  class="option active"
                  style="
                    --optionBackground: url(${Path1});
                  "
                >
                  <div class="shadow"></div>
                  <div class="label">
                    <div class="icon">
                      <i class="fas fa-expand"></i>
                    </div>
                  </div>
                </div>
                <div
                  class="option"
                  style="
                    --optionBackground: url(${Path2});
                  "
                >
                  <div class="shadow"></div>
                  <div class="label">
                    <div class="icon">
                      <i class="fas fa-expand"></i>
                    </div>
                  </div>
                </div>
                <div
                  class="option"
                  style="
                    --optionBackground: url(${Path4});
                  "
                >
                  <div class="shadow"></div>
                  <div class="label">
                    <div class="icon">
                      <i class="fas fa-expand"></i>
                    </div>
                  </div>
                </div>
                <div
                  class="option"
                  style="
                    --optionBackground: url(${Path3});
                  "
                >
                  <div class="shadow"></div>
                  <div class="label">
                    <div class="icon">
                      <i class="fas fa-expand"></i>
                    </div>
                  </div>
                </div>
                <div
                  class="option"
                  style="
                    --optionBackground: url(${Path1});
                  "
                >
                  <div class="shadow"></div>
                  <div class="label">
                    <div class="icon">
                      <i class="fas fa-expand"></i>
                    </div>
                  </div>
                </div>
              </div>`;
      AboutMainElement.style.backgroundImage = `url(${image})`;
      contentElement.innerHTML = options;
      titleDestinationElement.textContent = `${cityName}`;
      destinationImageElement.innerHTML = optionsImage;
      bestLocationElement.innerHTML = optionsBestImage;
    })
    .catch((error) => {
      console.error("Lỗi khi fetch dữ liệu:", error);
    });
});
function setRating(index) {
  stars.forEach((star, idx) => {
    if (idx <= index) {
      star.classList.add("hovered");
    } else {
      star.classList.remove("hovered");
    }
  });
  ratingText.textContent = stars[index].dataset.rating;
  selectedRating = index;
  valueRating = +(index + 1);
  return valueRating;
}
stars.forEach((star, index) => {
  star.addEventListener("mouseover", function () {
    stars.forEach((s, idx) => {
      if (idx <= index) {
        s.classList.add("hovered");
      } else {
        s.classList.remove("hovered");
      }
    });
    ratingText.textContent = star.dataset.rating;
  });
});
stars.forEach((star, index) => {
  star.addEventListener("click", function () {
    temp = setRating(index);
  });
});
btnRatingElement.addEventListener("click", function () {
  const idDestination = parseInt(idString, 10);
  console.log(temp, idDestination, contentDestinationElement.value);
});
